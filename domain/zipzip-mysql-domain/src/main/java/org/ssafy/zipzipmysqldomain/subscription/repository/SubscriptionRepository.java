package org.ssafy.zipzipmysqldomain.subscription.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ssafy.zipzipmysqldomain.subscription.dto.MySubscriptionDto;

public interface SubscriptionRepository {
    Page<MySubscriptionDto> getMyList(Pageable pageable, Long memberId);
}
