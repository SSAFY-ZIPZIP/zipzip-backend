package org.ssafy.zipzipapiapp.subscriptionProfile.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.zipzipapiapp.common.util.MemberUtil;
import org.ssafy.zipzipapiapp.subscriptionProfile.dto.PostSubscriptionProfileRequest;
import org.ssafy.zipzipapiapp.subscriptionProfile.service.SubscriptionProfileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/subscription-profile")
public class SubscriptionProfileController {

    private final SubscriptionProfileService subscriptionProfileService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createSubscriptionProfile(
            @Valid @RequestBody PostSubscriptionProfileRequest postSubscriptionProfileRequest,
            Authentication authentication) {
        Long memberId = MemberUtil.getUserId(authentication);
        subscriptionProfileService.postSubscriptionProfile(postSubscriptionProfileRequest, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
