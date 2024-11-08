package org.ssafy.zipzipexceptioncommon.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

    /**
     * 400 Bad Request
     */
    ERR_MISSING_AUTHORIZATION_CODE,

    /**
     * 401 UNAUTHORIZED
     */
    ERR_UNAUTORIZED,
    ERR_REFRESH_TOKEN_EXPIRED,
    ERR_ACCESS_TOKEN_EXPIRED,

    /**
     * 404 NOT_FOUND
     */
    ERR_NOT_FOUND_MEMBER,
    ;

}
