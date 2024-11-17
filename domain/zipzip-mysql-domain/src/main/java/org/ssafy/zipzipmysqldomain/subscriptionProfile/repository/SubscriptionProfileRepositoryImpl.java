package org.ssafy.zipzipmysqldomain.subscriptionProfile.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.subscriptionProfile.entity.SubscriptionProfile;

@Repository
@RequiredArgsConstructor
public class SubscriptionProfileRepositoryImpl implements SubscriptionProfileRepository {
    private final SubscriptionProfileJpaRepository subscriptionProfileJpaRepository;

    @Override
    public void save(SubscriptionProfile subscriptionProfile) {
        subscriptionProfileJpaRepository.save(subscriptionProfile);
    }
}
