package org.ssafy.zipzipmysqldomain.subscription.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.subscription.dto.MySubscriptionDto;

@Repository
@RequiredArgsConstructor
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    private final SubscriptionJpaRepository subscriptionJpaRepository;
    private final SubscriptionQueryDslRepository subscriptionQueryDslRepository;

    @Override
    public Page<MySubscriptionDto> getMyList(Pageable pageable, Long memberId) {
        return subscriptionQueryDslRepository.getMyList(pageable, memberId);
    }
}
