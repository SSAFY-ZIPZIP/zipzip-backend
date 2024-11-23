package org.ssafy.zipzipmysqldomain.subscription.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ssafy.zipzipmysqldomain.subscription.dto.SubscriptionFavoriteQueryResponseDto;
import org.ssafy.zipzipmysqldomain.subscription.dto.SubscriptionQueryResponseDto;
import org.ssafy.zipzipmysqldomain.subscription.dto.SubscriptionSearchQueryRequestDto;

public interface SubscriptionRepository {

    Page<SubscriptionFavoriteQueryResponseDto> findMyFavoriteList(Pageable pageable, Long memberId);

    Page<SubscriptionQueryResponseDto> findMyList(Pageable pageable, Long memberId);

    Page<SubscriptionQueryResponseDto> findSearchList(Pageable pageable,
                                                      SubscriptionSearchQueryRequestDto subscriptionSearchCommand,
                                                      Long searchId);
}
