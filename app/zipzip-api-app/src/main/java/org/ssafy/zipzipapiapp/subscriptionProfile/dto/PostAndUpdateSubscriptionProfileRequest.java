package org.ssafy.zipzipapiapp.subscriptionProfile.dto;

import jakarta.validation.constraints.NotNull;

public record PostAndUpdateSubscriptionProfileRequest(
        @NotNull(message = "memberCategory 값이 비어있습니다.") String memberCategory,
        @NotNull(message = "memberRegion 값이 비어있습니다.") String memberRegion) {
}
