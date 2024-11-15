package org.ssafy.zipzipmysqldomain.subscriptionFavorite.entity;

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

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscriptionFavorite extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long memberId;

    @Column
    private Long subscriptionId;

    @Builder
    public SubscriptionFavorite(Long memberId, Long subscriptionId) {
        this.memberId = memberId;
        this.subscriptionId = subscriptionId;
    }
}
