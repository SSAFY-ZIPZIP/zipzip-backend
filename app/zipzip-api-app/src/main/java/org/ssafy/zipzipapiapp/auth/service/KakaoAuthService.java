package org.ssafy.zipzipapiapp.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.ssafy.zipzipapiapp.auth.dto.SocialInfoDto;
import org.ssafy.zipzipapiapp.auth.dto.SocialLoginRequest;
import org.ssafy.zipzipkakaoclient.client.KakaoApiClient;
import org.ssafy.zipzipkakaoclient.client.KakaoAuthApiClient;
import org.ssafy.zipzipkakaoclient.dto.KakaoAccessTokenInfo;
import org.ssafy.zipzipkakaoclient.dto.KakaoAccount;
import org.ssafy.zipzipkakaoclient.dto.KakaoTokenResponse;
import org.ssafy.zipzipkakaoclient.dto.KakaoUserProfile;
import org.ssafy.zipzipkakaoclient.dto.KakaoUserResponse;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    @Value(value = "${kakao.clientId}")
    private String clientId;

    @Value(value = "${kakao.redirect-uri}")
    private String redirectUri;

    private static final String GRANT_TYPE = "authorization_code";

    private final KakaoAuthApiClient kakaoAuthApiClient;
    private final KakaoApiClient kakaoApiClient;

    public SocialInfoDto getKakaoUserData(SocialLoginRequest socialLoginRequest) {
        KakaoTokenResponse kakaoTokenResponse = kakaoAuthApiClient.getOAuth2Token(
                GRANT_TYPE,
                clientId,
                redirectUri,
                socialLoginRequest.code()
        );
        KakaoAccessTokenInfo kakaoAccessTokenInfo = kakaoApiClient.getAccessTokenInfo(
                "Bearer " + kakaoTokenResponse.getAccessToken());
        KakaoUserResponse kakaoUserResponse = kakaoApiClient.getUserInformation(
                "Bearer " + kakaoTokenResponse.getAccessToken());

        KakaoAccount kakaoAccount = kakaoUserResponse.getKakaoAccount();
        KakaoUserProfile kakaoUserProfile = kakaoAccount.getProfile();

        return new SocialInfoDto(
                kakaoAccessTokenInfo.getId(),
                kakaoAccount.getEmail(),
                kakaoUserProfile.getNickname(),
                kakaoUserProfile.getProfileImageUrl());
    }

}