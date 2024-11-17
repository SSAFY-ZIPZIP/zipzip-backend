package org.ssafy.zipzipexceptioncommon.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

    /**
     * 400 Bad Request
     */
    ERR_INVALID_REQUEST_BODY_FORMAT,
    ERR_MISSING_AUTHORIZATION_CODE,
    ERR_INVALID_SUBSCRIPTION_CATEGORY,
    ERR_INVALID_SUBSCRIPTION_REGION,

    /**
     * 401 UNAUTHORIZED
     */
    ERR_UNAUTORIZED,
    ERR_REFRESH_TOKEN_EXPIRED,
    ERR_ACCESS_TOKEN_EXPIRED,
    ERR_INVALID_TOKEN,
    ERR_EMPTY_TOKEN,

    /**
     * 404 NOT_FOUND
     */
    ERR_NOT_FOUND_MEMBER,

    /**
     * 500 INTERNAL_SERVER_ERROR
     */
    ERR_INTERNAL_SERVER_ERROR,
    ERR_INTERNAL_SERVER_SQL_ERROR;

}
