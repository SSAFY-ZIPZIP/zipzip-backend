package org.ssafy.zipzipapiapp.subscription.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ssafy.zipzipapiapp.common.dto.PageMetaDto;
import org.ssafy.zipzipapiapp.subscription.dto.GetMySubscriptionListResponse;
import org.ssafy.zipzipmysqldomain.subscription.dto.MySubscriptionDto;
import org.ssafy.zipzipmysqldomain.subscription.repository.SubscriptionRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public GetMySubscriptionListResponse getMyList(Pageable pageable, Long memberId) {
        Page<MySubscriptionDto> mySubscriptionDtoPage = subscriptionRepository.findMyList(pageable, memberId);
        return new GetMySubscriptionListResponse(mySubscriptionDtoPage.getContent(),
                new PageMetaDto(mySubscriptionDtoPage));
    }
}
