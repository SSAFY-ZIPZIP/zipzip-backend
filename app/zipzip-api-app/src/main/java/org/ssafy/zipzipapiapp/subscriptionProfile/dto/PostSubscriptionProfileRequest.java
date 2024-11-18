package org.ssafy.zipzipapiapp.subscriptionProfile.dto;

import jakarta.validation.constraints.NotBlank;

public record PostSubscriptionProfileRequest(@NotBlank(message = "memberCategory 값이 비어있습니다.") String memberCategory,
                                             @NotBlank(message = "memberRegion 값이 비어있습니다.") String memberRegion) {
}
