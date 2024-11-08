package org.ssafy.zipzipapiapp.common.filter;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_ACCESS_TOKEN_EXPIRED;
import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_REFRESH_TOKEN_EXPIRED;
import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_UNAUTORIZED;
import static org.ssafy.zipzipapiapp.common.jwt.JwtExceptionType.EMPTY_JWT;
import static org.ssafy.zipzipapiapp.common.jwt.JwtExceptionType.EXPIRED_JWT_TOKEN;
import static org.ssafy.zipzipapiapp.common.jwt.JwtExceptionType.VALID_JWT_TOKEN;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.ssafy.zipzipexceptioncommon.exception.UnAuthorizedException;
import org.ssafy.zipzipapiapp.common.jwt.JwtAuthenticationEntryPoint;
import org.ssafy.zipzipapiapp.common.jwt.JwtExceptionType;
import org.ssafy.zipzipapiapp.common.jwt.JwtTokenProvider;
import org.ssafy.zipzipapiapp.common.jwt.UserAuthentication;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private static final String REISSUE_API_URL = "/v1/oauth/reissue";
    private static final String LOGOUT_API_URL = "/v1/oauth/logout";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            String accessToken = jwtTokenProvider.resolveAccessToken(request);
            if (REISSUE_API_URL.equals(request.getRequestURI())) {
                handleTokenReissue(request);
            } else if (LOGOUT_API_URL.equals(request.getRequestURI())) {
                handleLogout(request);
            } else {
                validateAccessToken(accessToken);
            }
        } catch (UnAuthorizedException e) {
            jwtAuthenticationEntryPoint.setResponse(response, HttpStatus.UNAUTHORIZED, e.getMessage());
            return;
        }

        chain.doFilter(request, response);
    }

    private void handleTokenReissue(HttpServletRequest request) {
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
        validateRefreshToken(refreshToken);
        Long memberId = jwtTokenProvider.validateMemberRefreshToken(refreshToken);
        String newAccessToken = jwtTokenProvider.generateAccessToken(memberId);

        setAuthentication(newAccessToken);
        request.setAttribute("newAccessToken", newAccessToken);
    }

    private void handleLogout(HttpServletRequest request) {
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        request.setAttribute("refreshToken", refreshToken);
    }


    private void validateRefreshToken(String refreshToken) {
        JwtExceptionType jwtException = jwtTokenProvider.validateToken(refreshToken);
        if (jwtException == EMPTY_JWT) {
            throw new UnAuthorizedException(ERR_UNAUTORIZED);
        }
        if (jwtException == EXPIRED_JWT_TOKEN) {
            throw new UnAuthorizedException(ERR_REFRESH_TOKEN_EXPIRED);
        }
    }

    private void validateAccessToken(String accessToken) {
        if (accessToken != null) {
            JwtExceptionType jwtException = jwtTokenProvider.validateToken(accessToken);
            if (jwtException == VALID_JWT_TOKEN) {
                setAuthentication(accessToken);
            } else if (jwtException == EXPIRED_JWT_TOKEN) {
                throw new UnAuthorizedException(ERR_ACCESS_TOKEN_EXPIRED);
            }
        }
    }

    private void setAuthentication(String token) {
        Claims claims = jwtTokenProvider.getAccessTokenPayload(token);
        Authentication authentication = new UserAuthentication(Long.valueOf(String.valueOf(claims.get("id"))), null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
