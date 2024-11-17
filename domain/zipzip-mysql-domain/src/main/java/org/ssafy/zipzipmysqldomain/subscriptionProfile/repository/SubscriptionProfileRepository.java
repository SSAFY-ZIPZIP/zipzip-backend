package org.ssafy.zipzipmysqldomain.subscriptionProfile.repository;

import java.util.Optional;
import org.ssafy.zipzipmysqldomain.subscriptionProfile.entity.SubscriptionProfile;

public interface SubscriptionProfileRepository {
    void save(SubscriptionProfile subscriptionProfile);

    Optional<SubscriptionProfile> findByMemberId(Long memberId);
}
