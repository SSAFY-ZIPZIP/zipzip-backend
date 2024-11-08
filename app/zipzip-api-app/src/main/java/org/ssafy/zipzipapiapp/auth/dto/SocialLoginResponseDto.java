package org.ssafy.zipzipapiapp.auth.dto;

import jakarta.validation.constraints.NotNull;

public record SocialLoginResponseDto(
        @NotNull(message = "accessToken 값은 null일 수 없습니다.") String accessToken,
        @NotNull(message = "refreshToken 값은 null일 수 없습니다.") String refreshToken
) {
}