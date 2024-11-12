package org.ssafy.zipzipapiapp.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.zipzipapiapp.auth.dto.LoginResponse;
import org.ssafy.zipzipapiapp.auth.dto.SocialLoginRequest;
import org.ssafy.zipzipapiapp.auth.dto.TokenResponseDto;
import org.ssafy.zipzipapiapp.auth.service.AuthService;

@RestController
@RequestMapping("/v1/oauth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LoginResponse> login(@RequestBody SocialLoginRequest socialLoginRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.socialLogin(socialLoginRequest));
    }

    @GetMapping("/reissue")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenResponseDto> reissueToken(HttpServletRequest request) {
        String refreshToken = (String) request.getAttribute("refreshToken");
        TokenResponseDto response = authService.reissue(refreshToken);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new TokenResponseDto(response.accessToken(), response.refreshToken()));
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String refreshToken = (String) request.getAttribute("refreshToken");
        authService.logout(refreshToken);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
