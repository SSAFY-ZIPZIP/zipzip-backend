package org.ssafy.zipzipmysqldomain.subscription.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.subscription.dto.FavoriteSubscriptionDto;
import org.ssafy.zipzipmysqldomain.subscription.dto.MySubscriptionDto;

@Repository
@RequiredArgsConstructor
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    private final SubscriptionJpaRepository subscriptionJpaRepository;
    private final SubscriptionQueryDslRepository subscriptionQueryDslRepository;

    @Override
    public Page<MySubscriptionDto> findMyList(Pageable pageable, Long memberId) {
        return subscriptionQueryDslRepository.findMyList(pageable, memberId);
    }

    @Override
    public Page<FavoriteSubscriptionDto> findMyFavoriteList(Pageable pageable, Long memberId) {
        return subscriptionQueryDslRepository.findMyFavoriteList(pageable, memberId);
    }
}
