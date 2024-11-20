package org.ssafy.zipzipapiapp.subscription.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.zipzipapiapp.common.util.MemberUtil;
import org.ssafy.zipzipapiapp.subscription.dto.GetMySubscriptionListResponse;
import org.ssafy.zipzipapiapp.subscription.service.SubscriptionService;
import org.ssafy.zipzipapiapp.subscriptionFavorite.dto.GetFavoriteSubscriptionListResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/me/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetMySubscriptionListResponse> getMyList(Authentication authentication, Pageable pageable) {
        Long memberId = MemberUtil.getUserId(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionService.getMyList(pageable, memberId));
    }

    @GetMapping("/me/favorite/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetFavoriteSubscriptionListResponse> getList(Pageable pageable,
                                                                       Authentication authentication) {
        Long memberId = MemberUtil.getUserId(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionService.getMyFavoriteList(pageable, memberId));
    }
}
