package org.ssafy.zipzipmysqldomain.subscriptionProfile.repository;

import org.springframework.data.repository.CrudRepository;
import org.ssafy.zipzipmysqldomain.subscriptionProfile.entity.SubscriptionProfile;

public interface SubscriptionProfileJpaRepository extends CrudRepository<SubscriptionProfile, Long> {
}
