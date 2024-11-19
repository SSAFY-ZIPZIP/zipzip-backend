package org.ssafy.zipzipmysqldomain.member.repository;


import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipexceptioncommon.exception.ErrorMessage;
import org.ssafy.zipzipexceptioncommon.exception.NotFoundException;
import org.ssafy.zipzipmysqldomain.member.entity.Member;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    @Override
    public Optional<Member> findMemberBySocialId(String socialId) {
        return memberJpaRepository.findMemberBySocialId(socialId);
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

    @Override
    public Member findByIdOrThrow(Long id) {
        return memberJpaRepository.findMemberById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ERR_NOT_FOUND_MEMBER));
    }

    @Override
    public Optional<Long> findMemberIdByEmail(String email) {
        return memberJpaRepository.findMemberIdByEmail(email);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberJpaRepository.findById(id);
    }

}
