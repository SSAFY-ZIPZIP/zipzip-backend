package org.ssafy.zipzipmysqldomain.subscriptionProfile.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionCategory;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionRegion;
import org.ssafy.zipzipmysqldomain.subscriptionProfile.entity.SubscriptionProfile;

@Repository
@RequiredArgsConstructor
public class SubscriptionProfileRepositoryImpl implements SubscriptionProfileRepository {
    private final SubscriptionProfileJpaRepository subscriptionProfileJpaRepository;

    @Override
    public void save(SubscriptionProfile subscriptionProfile) {
        subscriptionProfileJpaRepository.save(subscriptionProfile);
    }

    @Override
    public Optional<SubscriptionProfile> findByMemberId(Long memberId) {
        return subscriptionProfileJpaRepository.findByMemberId(memberId);
    }

    @Override
    public int update(SubscriptionCategory memberCategory, SubscriptionRegion memberRegion, Long memberId) {
        return subscriptionProfileJpaRepository.update(memberCategory, memberRegion, memberId);
    }
}
