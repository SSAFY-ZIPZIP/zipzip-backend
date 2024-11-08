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
import org.ssafy.zipzipapiapp.auth.dto.LoginAccessTokenDto;
import org.ssafy.zipzipapiapp.auth.dto.SocialLoginRequestDto;
import org.ssafy.zipzipapiapp.auth.dto.SocialLoginResponseDto;
import org.ssafy.zipzipapiapp.auth.service.AuthService;

@RestController
@RequestMapping("/v1/oauth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LoginAccessTokenDto> login(
            @RequestBody SocialLoginRequestDto requestDto) {
        SocialLoginResponseDto responseDto = authService.socialLogin(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new LoginAccessTokenDto(responseDto.accessToken(), responseDto.refreshToken()));
    }

    @GetMapping("/reissue")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LoginAccessTokenDto> reissueToken(HttpServletRequest request) {
        String accessToken = (String) request.getAttribute("newAccessToken");
        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginAccessTokenDto(accessToken, null));
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String refreshToken = (String) request.getAttribute("refreshToken");
        authService.logout(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
