package org.ssafy.zipzipapiapp.auth.dto;

import jakarta.validation.constraints.NotNull;

public record SocialLoginRequest(
        @NotNull(message = "소셜 인가 코드 값은 null일 수 없습니다.") String code
) {
}
