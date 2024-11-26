package org.ssafy.zipzipapiapp.subscription.controller;

import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.zipzipapiapp.common.util.MemberUtil;
import org.ssafy.zipzipapiapp.subscription.dto.GetMySubscriptionListResponse;
import org.ssafy.zipzipapiapp.subscription.dto.GetSearchSubscriptionListResponse;
import org.ssafy.zipzipapiapp.subscription.dto.GetSubscriptionChatbotRequest;
import org.ssafy.zipzipapiapp.subscription.dto.GetSubscriptionChatbotResponse;
import org.ssafy.zipzipapiapp.subscription.service.SubscriptionService;
import org.ssafy.zipzipapiapp.subscriptionFavorite.dto.GetFavoriteSubscriptionListResponse;
import org.ssafy.zipzipapiapp.subscriptionProfile.dto.GetSubscriptionProfileResponse;
import org.ssafy.zipzipapiapp.subscriptionProfile.dto.PatchSubscriptionProfileRequest;
import org.ssafy.zipzipapiapp.subscriptionProfile.dto.PostSubscriptionProfileRequest;
import org.ssafy.zipzipgptclient.service.ChatGPTService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final ChatGPTService chatGPTService;

    @GetMapping("/me/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetMySubscriptionListResponse> getMyList(Authentication authentication, Pageable pageable) {
        Long memberId = MemberUtil.getUserId(authentication);
        GetMySubscriptionListResponse getMySubscriptionListResponse = subscriptionService.getMyList(pageable, memberId);

        return ResponseEntity.status(HttpStatus.OK).body(getMySubscriptionListResponse);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetSearchSubscriptionListResponse> getSearchList(Authentication authentication,
                                                                           @RequestParam(value = "apt-name", required = false) String aptName,
                                                                           @RequestParam(value = "category", required = false) String category,
                                                                           @RequestParam(value = "region", required = false) String region,
                                                                           Pageable pageable) {
        Long memberId = MemberUtil.getUserId(authentication);
        GetSearchSubscriptionListResponse getSearchSubscriptionListResponse = subscriptionService.getSearchList(
                pageable, aptName, category, region, memberId);
        return ResponseEntity.status(HttpStatus.OK).body(getSearchSubscriptionListResponse);
    }

    @GetMapping("/me/favorite/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetFavoriteSubscriptionListResponse> getList(Pageable pageable,
                                                                       Authentication authentication) {
        Long memberId = MemberUtil.getUserId(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionService.getMyFavoriteList(pageable, memberId));
    }

    @PostMapping("/profile/me")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> postProfile(
            @Valid @RequestBody PostSubscriptionProfileRequest postSubscriptionProfileRequest,
            Authentication authentication) {
        Long memberId = MemberUtil.getUserId(authentication);
        subscriptionService.postProfile(postSubscriptionProfileRequest, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/profile/me")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetSubscriptionProfileResponse> getProfile(Authentication authentication) {
        Long memberId = MemberUtil.getUserId(authentication);
        Optional<GetSubscriptionProfileResponse> getSubscriptionProfileResponse = subscriptionService.getProfile(
                memberId);
        if (getSubscriptionProfileResponse.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(getSubscriptionProfileResponse.get());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/profile/me")
    public ResponseEntity<Void> patchProfile(
            @Valid @RequestBody PatchSubscriptionProfileRequest patchSubscriptionProfileRequest,
            Authentication authentication) {
        Long memberId = MemberUtil.getUserId(authentication);
        subscriptionService.patchProfile(patchSubscriptionProfileRequest, memberId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("{subscriptionId}/me/favorite")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> postFavorite(@PathVariable Long subscriptionId, Authentication authentication) {
        Long memberId = MemberUtil.getUserId(authentication);
        subscriptionService.postFavorite(memberId, subscriptionId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{subscriptionId}/me/favorite")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long subscriptionId, Authentication authentication) {
        Long memberId = MemberUtil.getUserId(authentication);
        subscriptionService.deleteFavorite(memberId, subscriptionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/chat")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetSubscriptionChatbotResponse> sendChatbotMessage(
            @RequestBody GetSubscriptionChatbotRequest getSubscriptionChatbotRequest) {
        GetSubscriptionChatbotResponse getSubscriptionChatbotResponse = new GetSubscriptionChatbotResponse(
                chatGPTService.chatWithGPT(getSubscriptionChatbotRequest.userMessage()));
        return ResponseEntity.status(HttpStatus.OK).body(getSubscriptionChatbotResponse);
    }
}
