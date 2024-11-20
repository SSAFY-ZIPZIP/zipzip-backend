package org.ssafy.zipzipapiapp.subscriptionFavorite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.zipzipmysqldomain.subscriptionFavorite.entity.SubscriptionFavorite;
import org.ssafy.zipzipmysqldomain.subscriptionFavorite.repository.SubscriptionFavoriteRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionFavoriteService {

    private final SubscriptionFavoriteRepository subscriptionFavoriteRepository;

    @Transactional
    public void post(Long memberId, Long subscriptionId) {
        SubscriptionFavorite newSubscriptionFavorite = SubscriptionFavorite.builder()
                .memberId(memberId)
                .subscriptionId(subscriptionId)
                .build();
        subscriptionFavoriteRepository.save(newSubscriptionFavorite);
    }

    @Transactional
    public void delete(Long memberId, Long subscriptionId) {
        subscriptionFavoriteRepository.delete(memberId, subscriptionId);
    }
}
