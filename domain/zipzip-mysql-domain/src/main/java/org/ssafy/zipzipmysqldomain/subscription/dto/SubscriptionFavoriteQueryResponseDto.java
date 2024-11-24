package org.ssafy.zipzipmysqldomain.subscription.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionCategory;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionRegion;

public record SubscriptionFavoriteQueryResponseDto(Long subscriptionId,
                                                   LocalDateTime deadline,
                                                   String aptName,
                                                   String category,
                                                   String region,
                                                   String address,
                                                   Integer generalHouseHold,
                                                   Integer specialHouseHold,
                                                   Boolean isSubscribedToNotification,
                                                   String url) {
    @QueryProjection
    public SubscriptionFavoriteQueryResponseDto(Long subscriptionId, LocalDateTime deadline, String aptName,
                                                SubscriptionCategory category, SubscriptionRegion region,
                                                String address, int generalHouseHold, int specialHouseHold,
                                                boolean isFavorite, String url) {
        this(subscriptionId, deadline, aptName, category.getDescription(), region.getDescription(), address,
                generalHouseHold, specialHouseHold, isFavorite, url);
    }
}
