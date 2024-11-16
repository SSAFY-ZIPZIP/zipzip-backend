package org.ssafy.zipzipmysqldomain.subscription.enums;

import static org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionSupplyType.GENERAL;
import static org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionSupplyType.SPECIAL;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum SubscriptionCategory {

    ALL(GENERAL, "일반"),
    NEWLYWED(SPECIAL, "신혼부부"),
    MULTI_CHILD(SPECIAL, "다자녀가구"),
    FIRST_TIME(SPECIAL, "생애최초"),
    YOUTH(SPECIAL, "청년"),
    ELDERLY_SUPPORT(SPECIAL, "노부모부양"),
    NEWBORN(SPECIAL, "신생아"),
    INSTITUTION(SPECIAL, "기관추천"),
    RELOCATION(SPECIAL, "이전기관");

    private final SubscriptionSupplyType type;
    private final String description;
}
