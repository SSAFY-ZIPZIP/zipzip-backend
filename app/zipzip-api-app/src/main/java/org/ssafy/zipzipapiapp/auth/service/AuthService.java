package org.ssafy.zipzipapiapp.auth.service;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_INTERNAL_SERVER_ERROR;
import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_MISSING_AUTHORIZATION_CODE;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.zipzipapiapp.auth.dto.SocialInfoDto;
import org.ssafy.zipzipapiapp.auth.dto.SocialLoginRequestDto;
import org.ssafy.zipzipapiapp.auth.dto.TokenResponseDto;
import org.ssafy.zipzipapiapp.common.jwt.JwtTokenProvider;
import org.ssafy.zipzipexceptioncommon.exception.BadRequestException;
import org.ssafy.zipzipexceptioncommon.exception.InternalServerException;
import org.ssafy.zipzipmysqldomain.common.entity.Member;
import org.ssafy.zipzipmysqldomain.member.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final KakaoAuthService kakaoAuthService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Transactional
    public TokenResponseDto socialLogin(SocialLoginRequestDto request) {

        if (request.code() == null) {
            throw new BadRequestException(ERR_MISSING_AUTHORIZATION_CODE);
        }

        try {
            SocialInfoDto socialInfo = kakaoAuthService.getKakaoUserData(request);
            Member member = findMember(socialInfo);
            return generateTokens(member);
        } catch (Exception ex) {
            log.error("Social login failed: {}", ex.getMessage(), ex);
            throw new InternalServerException(ERR_INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void logout(String refreshToken) {
        Member member = memberRepository.findByRefreshTokenOrThrow(refreshToken);
        memberRepository.updateRefreshToken(null, member.getId());
    }

    @Transactional
    public TokenResponseDto reissue(String refreshToken) {
        Member member = memberRepository.findByRefreshTokenOrThrow(refreshToken);
        return generateTokens(member);
    }

    private Member findMember(SocialInfoDto socialInfo) {
        String socialId = String.valueOf(socialInfo.id());
        return memberRepository.findMemberBySocialId(socialId)
                .orElseGet(() -> signUpMember(socialInfo));
    }

    private Member signUpMember(SocialInfoDto socialInfo) {
        Member newMember = Member.builder()
                .nickname(socialInfo.nickname())
                .email(socialInfo.email())
                .socialId(String.valueOf(socialInfo.id()))
                .profileImage(socialInfo.profileImage())
                .build();
        memberRepository.save(newMember);
        return memberRepository.findMemberBySocialIdOrThrow(newMember.getSocialId());
    }

    private TokenResponseDto generateTokens(Member member) {
        String newRefreshToken = jwtTokenProvider.generateRefreshToken();
        String newAccessToken = jwtTokenProvider.generateAccessToken(member.getId());

        memberRepository.updateRefreshToken(newRefreshToken, member.getId());
        return new TokenResponseDto(newAccessToken, newRefreshToken);
    }
}