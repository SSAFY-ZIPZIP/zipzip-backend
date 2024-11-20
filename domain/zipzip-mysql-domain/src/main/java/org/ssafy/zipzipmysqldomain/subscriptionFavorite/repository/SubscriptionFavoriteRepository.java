package org.ssafy.zipzipmysqldomain.subscriptionFavorite.repository;

import org.ssafy.zipzipmysqldomain.subscriptionFavorite.entity.SubscriptionFavorite;

public interface SubscriptionFavoriteRepository {
    void save(SubscriptionFavorite subscriptionFavorite);

    void delete(Long memberId, Long subscriptionId);
}
