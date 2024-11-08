package org.ssafy.zipzipkakaoclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.ssafy.zipzipkakaoclient.dto.KakaoAccessTokenInfo;
import org.ssafy.zipzipkakaoclient.dto.KakaoUserResponse;

@Component
@FeignClient(value = "kakaoApiClient", url = "https://kapi.kakao.com")
public interface KakaoApiClient {

    @GetMapping(value = "/v2/user/me")
    KakaoUserResponse getUserInformation(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);

    @GetMapping(value = "/v1/user/access_token_info")
    KakaoAccessTokenInfo getAccessTokenInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);
}