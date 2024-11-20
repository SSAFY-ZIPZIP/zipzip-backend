package org.ssafy.zipzipmysqldomain.subscriptionProfile.dto;

import com.querydsl.core.annotations.QueryProjection;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionCategory;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionRegion;

public record SubscriptionProfileDto(SubscriptionRegion memberRegion, SubscriptionCategory memberCategory,
                                     Boolean isNotificationSubscription) {
    @QueryProjection
    public SubscriptionProfileDto {
    }
}
