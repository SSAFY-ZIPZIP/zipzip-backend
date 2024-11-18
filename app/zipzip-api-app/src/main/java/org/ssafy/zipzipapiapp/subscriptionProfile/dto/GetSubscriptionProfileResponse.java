package org.ssafy.zipzipapiapp.subscriptionProfile.dto;

public record GetSubscriptionProfileResponse(String memberCategory, String memberRegion,
                                             Boolean isNotificationSubscription) {
}
