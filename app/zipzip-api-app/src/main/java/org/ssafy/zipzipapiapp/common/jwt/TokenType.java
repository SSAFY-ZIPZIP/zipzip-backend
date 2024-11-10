package org.ssafy.zipzipapiapp.common.jwt;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_ACCESS_TOKEN_EXPIRED;
import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_REFRESH_TOKEN_EXPIRED;

import org.ssafy.zipzipexceptioncommon.exception.ErrorMessage;

public enum TokenType {
    ACCESS {
        @Override
        public ErrorMessage getExpiredMessage() {
            return ERR_ACCESS_TOKEN_EXPIRED;
        }
    },
    REFRESH {
        @Override
        public ErrorMessage getExpiredMessage() {
            return ERR_REFRESH_TOKEN_EXPIRED;
        }
    };

    public abstract ErrorMessage getExpiredMessage();
}
