package org.ssafy.zipzipapiapp.auth.dto;

import jakarta.validation.constraints.NotNull;

public record TokenResponseDto(
        @NotNull String accessToken,
        @NotNull String refreshToken
) {
}