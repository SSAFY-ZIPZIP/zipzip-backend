package org.ssafy.zipzipmysqldomain.subscription.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.subscription.dto.SubscriptionFavoriteQueryResponseDto;
import org.ssafy.zipzipmysqldomain.subscription.dto.SubscriptionQueryResponseDto;
import org.ssafy.zipzipmysqldomain.subscription.dto.SubscriptionSearchQueryRequestDto;

@Repository
@RequiredArgsConstructor
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    private final SubscriptionJpaRepository subscriptionJpaRepository;
    private final SubscriptionQueryDslRepository subscriptionQueryDslRepository;

    @Override
    public Page<SubscriptionQueryResponseDto> findMyList(Pageable pageable, Long memberId) {
        return subscriptionQueryDslRepository.findMyList(pageable, memberId);
    }

    @Override
    public Page<SubscriptionFavoriteQueryResponseDto> findMyFavoriteList(Pageable pageable, Long memberId) {
        return subscriptionQueryDslRepository.findMyFavoriteList(pageable, memberId);
    }

    @Override
    public Page<SubscriptionQueryResponseDto> findSearchList(Pageable pageable,
                                                             SubscriptionSearchQueryRequestDto subscriptionSearchQueryRequestDto,
                                                             Long memberId) {
        return subscriptionQueryDslRepository.findSearchList(pageable, subscriptionSearchQueryRequestDto, memberId);
    }
}
