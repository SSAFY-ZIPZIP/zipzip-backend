package org.ssafy.zipzipapiapp.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.ssafy.zipzipkakaoclient.client.KakaoApiClient;
import org.ssafy.zipzipkakaoclient.client.KakaoAuthApiClient;
import org.ssafy.zipzipkakaoclient.dto.KakaoAccessTokenInfo;
import org.ssafy.zipzipkakaoclient.dto.KakaoTokenResponse;
import org.ssafy.zipzipkakaoclient.dto.KakaoUserResponse;
import org.ssafy.zipzipapiapp.auth.dto.SocialInfoDto;
import org.ssafy.zipzipapiapp.auth.dto.SocialLoginRequestDto;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    @Value(value="${kakao.clientId}")
    private String clientId;

    @Value(value = "${kakao.redirect-uri}")
    private String redirectUri;

    private static final String GRANT_TYPE = "authorization_code";

    private final KakaoAuthApiClient kakaoAuthApiClient;
    private final KakaoApiClient kakaoApiClient;

    public SocialInfoDto getKakaoUserData(SocialLoginRequestDto socialLoginRequestDto) {
        KakaoTokenResponse tokenResponse = kakaoAuthApiClient.getOAuth2Token(
                GRANT_TYPE,
                clientId,
                redirectUri,
                socialLoginRequestDto.code()
        );
        KakaoAccessTokenInfo tokenInfo = kakaoApiClient.getAccessTokenInfo("Bearer " + tokenResponse.getAccessToken());
        KakaoUserResponse userResponse = kakaoApiClient.getUserInformation("Bearer " + tokenResponse.getAccessToken());
        return new SocialInfoDto(tokenInfo.getId(), userResponse.getKakaoAccount().getProfile().getEmail(), userResponse.getKakaoAccount().getProfile().getNickname());
    }

}