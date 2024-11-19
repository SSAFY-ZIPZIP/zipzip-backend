package org.ssafy.zipzipmysqldomain.subscription.repository;

import org.springframework.data.repository.CrudRepository;
import org.ssafy.zipzipmysqldomain.subscription.entity.Subscription;

public interface SubscriptionJpaRepository extends CrudRepository<Subscription, Long> {
}
