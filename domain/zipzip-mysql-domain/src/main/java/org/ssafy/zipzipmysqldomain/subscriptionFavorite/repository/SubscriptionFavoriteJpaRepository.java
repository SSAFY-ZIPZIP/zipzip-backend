package org.ssafy.zipzipmysqldomain.subscriptionFavorite.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.ssafy.zipzipmysqldomain.subscriptionFavorite.entity.SubscriptionFavorite;

public interface SubscriptionFavoriteJpaRepository extends CrudRepository<SubscriptionFavorite, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM SubscriptionFavorite sf WHERE sf.memberId = :memberId AND sf.subscriptionId = :subscriptionId")
    void delete(@Param("memberId") Long memberId, @Param("subscriptionId") Long subscriptionId);
}
