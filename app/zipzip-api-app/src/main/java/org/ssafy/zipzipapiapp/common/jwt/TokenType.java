package org.ssafy.zipzipapiapp.common.jwt;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_ACCESS_TOKEN_EXPIRED;
import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_REFRESH_TOKEN_EXPIRED;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.ssafy.zipzipexceptioncommon.exception.ErrorMessage;

@Getter
@RequiredArgsConstructor
public enum TokenType {

    ACCESS(ERR_ACCESS_TOKEN_EXPIRED),
    REFRESH(ERR_REFRESH_TOKEN_EXPIRED);

    private final ErrorMessage expiredMessage;
}
