package org.ssafy.zipzipapiapp.common.filter;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_EMPTY_TOKEN;
import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_INVALID_TOKEN;

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
import org.ssafy.zipzipapiapp.common.jwt.JwtAuthenticationEntryPoint;
import org.ssafy.zipzipapiapp.common.jwt.JwtExceptionType;
import org.ssafy.zipzipapiapp.common.jwt.JwtTokenProvider;
import org.ssafy.zipzipapiapp.common.jwt.TokenType;
import org.ssafy.zipzipapiapp.common.jwt.UserAuthentication;
import org.ssafy.zipzipexceptioncommon.exception.UnAuthorizedException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String REISSUE_API_URL = "/v1/oauth/reissue";
    private static final String LOGOUT_API_URL = "/v1/oauth/logout";
    private static final String LOGIN_API_URL = "/v1/oauth/login";

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 로그인의 경우, accessToken으로 검증할 필요 없음
        if (isLoginRequest(request)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            if (isTokenReissueOrLogoutRequest(request)) {
                handleRefreshTokenValidation(request);
            } else {
                handleAccessTokenValidation(request);
            }
            chain.doFilter(request, response);
        } catch (UnAuthorizedException e) {
            jwtAuthenticationEntryPoint.sendErrorResponse(response, HttpStatus.UNAUTHORIZED, e.getErrorMessage());
        }
    }

    private boolean isLoginRequest(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        return LOGIN_API_URL.equals(requestUri);
    }

    private boolean isTokenReissueOrLogoutRequest(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        return REISSUE_API_URL.equals(requestUri) || LOGOUT_API_URL.equals(requestUri);
    }

    private void handleAccessTokenValidation(HttpServletRequest request) {
        String accessToken = jwtTokenProvider.resolveAccessToken(request);
        validateToken(accessToken, TokenType.ACCESS);
        setAuthentication(accessToken);
    }

    private void handleRefreshTokenValidation(HttpServletRequest request) {
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
        validateToken(refreshToken, TokenType.REFRESH);
        request.setAttribute("refreshToken", refreshToken);
    }

    private void validateToken(String token, TokenType tokenType) {
        JwtExceptionType jwtException = jwtTokenProvider.validateToken(token);

        if (jwtException == JwtExceptionType.VALID_JWT_TOKEN) {
            return;
        }

        switch (jwtException) {
            case EMPTY_JWT -> throw new UnAuthorizedException(ERR_EMPTY_TOKEN);
            case EXPIRED_JWT_TOKEN -> throw new UnAuthorizedException(tokenType.getExpiredMessage());
            case INVALID_JWT_TOKEN -> throw new UnAuthorizedException(ERR_INVALID_TOKEN);
        }
    }

    private void setAuthentication(String token) {
        Claims claims = jwtTokenProvider.getAccessTokenPayload(token);
        Long userId = Long.valueOf(String.valueOf(claims.get("id")));
        Authentication authentication = new UserAuthentication(userId, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}