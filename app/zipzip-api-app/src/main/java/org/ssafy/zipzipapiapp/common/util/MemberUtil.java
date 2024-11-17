package org.ssafy.zipzipapiapp.common.util;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_UNAUTORIZED;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.ssafy.zipzipexceptioncommon.exception.UnAuthorizedException;

@RequiredArgsConstructor
public class MemberUtil {

    public static Long getUserId(Authentication authentication) {
        if (authentication == null) {
            throw new UnAuthorizedException(ERR_UNAUTORIZED);
        }
        return Long.valueOf(authentication.getName());
    }
}