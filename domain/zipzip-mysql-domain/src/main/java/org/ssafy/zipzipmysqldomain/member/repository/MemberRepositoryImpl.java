package org.ssafy.zipzipmysqldomain.member.repository;


import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipexceptioncommon.exception.ErrorMessage;
import org.ssafy.zipzipexceptioncommon.exception.NotFoundException;
import org.ssafy.zipzipmysqldomain.common.entity.Member;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    @Override
    public Optional<Member> findMemberById(Long id) {
        return memberJpaRepository.findMemberById(id);
    }

    @Override
    public Optional<Member> findMemberByRefreshToken(String refreshToken) {
        return memberJpaRepository.findMemberByRefreshToken(refreshToken);
    }

    @Override
    public Optional<Member> findMemberBySocialId(String socialId) {
        return memberJpaRepository.findMemberBySocialId(socialId);
    }

    @Override
    public Member findMemberByIdOrThrow(Long memberId) {
        return memberJpaRepository.findMemberById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ERR_NOT_FOUND_MEMBER));
    }

    @Override
    public Member findByRefreshTokenOrThrow(String refreshToken) {
        return memberJpaRepository.findMemberByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ERR_NOT_FOUND_MEMBER));
    }

    @Override
    public Member findMemberBySocialIdOrThrow(String socialId) {
        return memberJpaRepository.findMemberBySocialId(socialId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ERR_NOT_FOUND_MEMBER));
    }

    @Override
    public void updateRefreshToken(String refreshToken, Long id) {
        memberJpaRepository.updateRefreshToken(refreshToken, id);
    }


}
