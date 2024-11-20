package org.ssafy.zipzipmysqldomain.subscription.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionCategory;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionRegion;

public record FavoriteSubscriptionDto(Long subscriptionId,
                                      LocalDateTime deadline,
                                      String aptName,
                                      SubscriptionCategory category,
                                      SubscriptionRegion region,
                                      String address,
                                      Integer generalHouseHold,
                                      Integer specialHouseHold,
                                      Boolean isSubscribedToNotification,
                                      String url) {
    @QueryProjection
    public FavoriteSubscriptionDto {
    }
}
