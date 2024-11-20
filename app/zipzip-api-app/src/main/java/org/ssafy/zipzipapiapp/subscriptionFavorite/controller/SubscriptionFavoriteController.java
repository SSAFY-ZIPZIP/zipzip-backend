package org.ssafy.zipzipapiapp.subscriptionFavorite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.zipzipapiapp.common.util.MemberUtil;
import org.ssafy.zipzipapiapp.subscriptionFavorite.service.SubscriptionFavoriteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/subscriptions")
public class SubscriptionFavoriteController {

    private final SubscriptionFavoriteService subscriptionFavoriteService;

    @PostMapping("{subscriptionId}/me/favorite")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> post(@PathVariable Long subscriptionId, Authentication authentication) {
        Long memberId = MemberUtil.getUserId(authentication);
        subscriptionFavoriteService.post(memberId, subscriptionId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
