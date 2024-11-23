package org.ssafy.zipzipmysqldomain.subscription.dto;

import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionCategory;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionRegion;

public record SubscriptionSearchQueryRequestDto(String aptName,
                                                SubscriptionCategory category,
                                                SubscriptionRegion region) {
}