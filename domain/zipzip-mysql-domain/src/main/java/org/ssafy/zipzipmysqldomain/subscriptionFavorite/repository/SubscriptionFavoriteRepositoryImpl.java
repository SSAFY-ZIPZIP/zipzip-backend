package org.ssafy.zipzipmysqldomain.subscriptionFavorite.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.subscriptionFavorite.entity.SubscriptionFavorite;

@Repository
@RequiredArgsConstructor
public class SubscriptionFavoriteRepositoryImpl implements SubscriptionFavoriteRepository {

    private final SubscriptionFavoriteJpaRepository subscriptionFavoriteJpaRepository;

    @Override
    public void save(SubscriptionFavorite subscriptionFavorite) {
        subscriptionFavoriteJpaRepository.save(subscriptionFavorite);
    }

    @Override
    public void delete(Long memberId, Long subscriptionId) {
        subscriptionFavoriteJpaRepository.delete(memberId, subscriptionId);
    }
}
