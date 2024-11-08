package org.ssafy.zipzipapiapp.auth.service;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_MISSING_AUTHORIZATION_CODE;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.zipzipexceptioncommon.exception.BadRequestException;
import org.ssafy.zipzipapiapp.auth.dto.SocialInfoDto;
import org.ssafy.zipzipapiapp.auth.dto.SocialLoginRequestDto;
import org.ssafy.zipzipapiapp.auth.dto.SocialLoginResponseDto;
import org.ssafy.zipzipapiapp.common.jwt.JwtTokenProvider;
import org.ssafy.zipzipmysqldomain.common.entity.Member;
import org.ssafy.zipzipmysqldomain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final KakaoAuthService kakaoAuthService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;


    public SocialLoginResponseDto socialLogin(SocialLoginRequestDto requestDto) {
        if (requestDto.code() == null) {
            throw new BadRequestException(ERR_MISSING_AUTHORIZATION_CODE);
        }

        try {
            SocialInfoDto socialData = kakaoAuthService.getKakaoUserData(requestDto);
            String refreshToken = jwtTokenProvider.generateRefreshToken();
            Optional<Member> findMember = memberRepository.findMemberBySocialId(String.valueOf(socialData.id()));

            // 신규 유저 저장
            if (findMember.isEmpty()) {
                Member member = Member.builder()
                        .nickname(socialData.nickname())
                        .email(socialData.email())
                        .socialId(String.valueOf(socialData.id()))
                        .build();

                memberRepository.save(member);
            }

            // socialId를 통해서 등록된 유저 찾기
            Member signedMember = memberRepository.findMemberBySocialIdOrThrow(String.valueOf(socialData.id()));
            System.out.println(signedMember.getSocialId());
            memberRepository.updateRefreshToken(refreshToken,signedMember.getId());
            // access token 만들기
            String accessToken = jwtTokenProvider.generateAccessToken(signedMember.getId());

            return new SocialLoginResponseDto(accessToken, refreshToken);

        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException();
        }
    }

    public void logout(String refreshToken) {
        Member member = memberRepository.findByRefreshTokenOrThrow(refreshToken);
        memberRepository.updateRefreshToken(null, member.getId());
    }

}
