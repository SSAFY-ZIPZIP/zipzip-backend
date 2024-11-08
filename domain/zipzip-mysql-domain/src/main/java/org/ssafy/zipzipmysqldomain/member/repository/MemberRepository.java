package org.ssafy.zipzipmysqldomain.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.ssafy.zipzipmysqldomain.common.entity.Member;

public interface MemberRepository {

    void save(Member member);

    Optional<Member> findMemberById(Long id);
    Optional<Member> findMemberByRefreshToken(String refreshToken);
    Optional<Member> findMemberBySocialId(String socialId);

    Member findMemberByIdOrThrow(Long memberId);
    Member findByRefreshTokenOrThrow(String refreshToken);
    Member findMemberBySocialIdOrThrow(String socialId);

    void updateRefreshToken(String refreshToken, Long id);


}


