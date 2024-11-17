package org.ssafy.zipzipmysqldomain.subscriptionProfile.repository;

import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionCategory;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionRegion;
import org.ssafy.zipzipmysqldomain.subscriptionProfile.entity.SubscriptionProfile;

public interface SubscriptionProfileJpaRepository extends CrudRepository<SubscriptionProfile, Long> {
    Optional<SubscriptionProfile> findByMemberId(Long memberId);

    @Modifying
    @Transactional
    @Query("UPDATE SubscriptionProfile s SET s.memberCategory = :memberCategory, s.memberRegion = :memberRegion WHERE s.memberId = :memberId")
    int updateSubscriptionProfile(@Param("memberCategory") SubscriptionCategory memberCategory,
                                  @Param("memberRegion") SubscriptionRegion memberRegion,
                                  @Param("memberId") Long memberId);
}
