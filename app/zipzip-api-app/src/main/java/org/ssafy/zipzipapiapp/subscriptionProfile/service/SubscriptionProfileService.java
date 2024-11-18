package org.ssafy.zipzipapiapp.subscriptionProfile.service;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_INTERNAL_SERVER_SQL_ERROR;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.zipzipapiapp.subscriptionProfile.dto.GetSubscriptionProfileResponse;
import org.ssafy.zipzipapiapp.subscriptionProfile.dto.PostAndUpdateSubscriptionProfileRequest;
import org.ssafy.zipzipexceptioncommon.exception.InternalServerException;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionCategory;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionRegion;
import org.ssafy.zipzipmysqldomain.subscriptionProfile.entity.SubscriptionProfile;
import org.ssafy.zipzipmysqldomain.subscriptionProfile.repository.SubscriptionProfileRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionProfileService {

    private final SubscriptionProfileRepository subscriptionProfileRepository;

    @Transactional
    public void postSubscriptionProfile(PostAndUpdateSubscriptionProfileRequest postAndUpdateSubscriptionProfileRequest,
                                        Long memberId) {
        SubscriptionProfile newSubscriptionProfile = SubscriptionProfile.builder()
                .memberId(memberId)
                .memberCategory(
                        SubscriptionCategory.findByDescription(
                                postAndUpdateSubscriptionProfileRequest.memberCategory()))
                .memberRegion(
                        SubscriptionRegion.findByDescription(postAndUpdateSubscriptionProfileRequest.memberRegion()))
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

    @Transactional
    public void updateSubscriptionProfile(
            PostAndUpdateSubscriptionProfileRequest postAndUpdateSubscriptionProfileRequest,
            Long memberId) {
        SubscriptionCategory memberCategory = SubscriptionCategory.findByDescription(
                postAndUpdateSubscriptionProfileRequest.memberCategory());
        SubscriptionRegion memberRegion = SubscriptionRegion.findByDescription(
                postAndUpdateSubscriptionProfileRequest.memberRegion());
        int updateResult = subscriptionProfileRepository.update(memberCategory, memberRegion, memberId);
        if (updateResult < 1) {
            throw new InternalServerException(ERR_INTERNAL_SERVER_SQL_ERROR);
        }
    }
}
