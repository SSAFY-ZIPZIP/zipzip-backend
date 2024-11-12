package org.ssafy.zipzipmysqldomain.member.repository;

import java.util.Optional;
import org.ssafy.zipzipmysqldomain.member.entity.Member;

public interface MemberRepository {

    void save(Member member);

    Optional<Member> findMemberBySocialId(String socialId);

    Member findByRefreshTokenOrThrow(String refreshToken);

    Member findMemberBySocialIdOrThrow(String socialId);

    void updateRefreshToken(String refreshToken, Long id);

    Optional<Member> findMemberById(Long id);


}


