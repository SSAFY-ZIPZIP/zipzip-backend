package org.ssafy.zipzipapiapp.subscriptionProfile.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.zipzipapiapp.subscriptionProfile.dto.GetSubscriptionProfileResponse;
import org.ssafy.zipzipapiapp.subscriptionProfile.dto.PostSubscriptionProfileRequest;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionCategory;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionRegion;
import org.ssafy.zipzipmysqldomain.subscriptionProfile.entity.SubscriptionProfile;
import org.ssafy.zipzipmysqldomain.subscriptionProfile.repository.SubscriptionProfileRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionProfileService {

    private final SubscriptionProfileRepository subscriptionProfileRepository;

    @Transactional
    public void post(PostSubscriptionProfileRequest postSubscriptionProfileRequest, Long memberId) {
        SubscriptionProfile newSubscriptionProfile = SubscriptionProfile.builder()
                .memberId(memberId)
                .memberCategory(SubscriptionCategory.findByDescription(postSubscriptionProfileRequest.memberCategory()))
                .memberRegion(SubscriptionRegion.findByDescription(postSubscriptionProfileRequest.memberRegion()))
                .isNotificationSubscription(false)
                .build();
        subscriptionProfileRepository.save(newSubscriptionProfile);
    }

    public Optional<GetSubscriptionProfileResponse> getSubscriptionProfile(Long memberId) {
        return subscriptionProfileRepository.findByMemberId(memberId)
                .map(profile -> new GetSubscriptionProfileResponse(
                        profile.getMemberCategory().getDescription(),
                        profile.getMemberRegion().getDescription(),
                        profile.getIsNotificationSubscription()
                ));
    }
}
