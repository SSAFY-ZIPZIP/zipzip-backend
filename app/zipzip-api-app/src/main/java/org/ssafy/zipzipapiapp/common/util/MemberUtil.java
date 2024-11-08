package org.ssafy.zipzipapiapp.common.util;
import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_UNAUTORIZED;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.ssafy.zipzipexceptioncommon.exception.UnAuthorizedException;

@RequiredArgsConstructor
public class MemberUtil {

    public static Long getUserId(Principal principal) {
        if (principal == null) {
            throw new UnAuthorizedException(ERR_UNAUTORIZED);
        }
        return Long.valueOf(principal.getName());
    }
}