package org.ssafy.zipzipmysqldomain.subscription.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
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
public class Subscription extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(nullable = false)
    private String aptName;

    @Column(nullable = false)
    private SubscriptionCategory category;

    @Column(nullable = false)
    private SubscriptionRegion region;

    @Column(nullable = false)
    private String address;

    @Column
    private Integer generalHouseHold;

    @Column
    private Integer specialHouseHold;

    @Column
    private String url;

    @Builder
    public Subscription(LocalDateTime deadline, String aptName, SubscriptionCategory category,
                        SubscriptionRegion region, String address, Integer generalHouseHold, Integer specialHouseHold,
                        String url) {
        this.deadline = deadline;
        this.aptName = aptName;
        this.category = category;
        this.region = region;
        this.address = address;
        this.generalHouseHold = generalHouseHold;
        this.specialHouseHold = specialHouseHold;
        this.url = url;
    }
}
