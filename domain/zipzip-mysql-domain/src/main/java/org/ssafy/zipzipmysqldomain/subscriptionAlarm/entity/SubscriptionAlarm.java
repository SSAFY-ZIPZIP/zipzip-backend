package org.ssafy.zipzipmysqldomain.subscriptionAlarm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssafy.zipzipmysqldomain.common.entity.BaseTimeEntity;
import org.ssafy.zipzipmysqldomain.subscriptionAlarm.enums.SendStatus;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscriptionAlarm extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long subscriptionId;

    @Column(nullable = false)
    private SendStatus sendStatus;

    @Builder
    public SubscriptionAlarm(Long subscriptionId, SendStatus sendStatus) {
        this.subscriptionId = subscriptionId;
        this.sendStatus = sendStatus;
    }
}
