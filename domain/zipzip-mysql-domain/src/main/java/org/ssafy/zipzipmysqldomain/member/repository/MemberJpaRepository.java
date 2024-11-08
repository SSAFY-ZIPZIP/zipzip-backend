package org.ssafy.zipzipmysqldomain.member.repository;

import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.ssafy.zipzipmysqldomain.common.entity.Member;

public interface MemberJpaRepository extends CrudRepository<Member, Long> {
    Optional<Member> findMemberById(Long id);

    @Query("SELECT m FROM Member m WHERE m.refreshToken = :refreshToken")
    Optional<Member> findMemberByRefreshToken(@Param("refreshToken") String refreshToken);

    Optional<Member> findMemberBySocialId(String socialId);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.refreshToken = :refreshToken WHERE m.id = :id")
    void updateRefreshToken(@Param("refreshToken") String refreshToken, @Param("id") Long id);


}
