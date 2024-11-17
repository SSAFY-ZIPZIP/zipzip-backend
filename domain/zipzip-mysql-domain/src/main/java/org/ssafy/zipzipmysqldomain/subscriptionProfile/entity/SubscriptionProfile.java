package org.ssafy.zipzipmysqldomain.subscriptionProfile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssafy.zipzipmysqldomain.common.entity.BaseTimeEntity;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionCategory;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionRegion;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscriptionProfile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SubscriptionRegion memberRegion;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SubscriptionCategory memberCategory;

    @Column(nullable = false)
    private Boolean isNotificationSubscription;

    @Builder
    public SubscriptionProfile(Long memberId, SubscriptionRegion memberRegion, SubscriptionCategory memberCategory,
                               Boolean isNotificationSubscription) {
        this.memberId = memberId;
        this.memberRegion = memberRegion;
        this.memberCategory = memberCategory;
        this.isNotificationSubscription = isNotificationSubscription;
    }
}
