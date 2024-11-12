package org.ssafy.zipzipapiapp.auth.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        Long memberId
) {
}
