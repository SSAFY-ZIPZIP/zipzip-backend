package org.ssafy.zipzipmysqldomain.workspacePropertyDeal.entity;

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
public class WorkspacePropertyDeal extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long propertyDealId;

    @Column(nullable = false)
    private Long workspaceId;

    @Builder
    public WorkspacePropertyDeal(Long propertyDealId, Long workspaceId) {
        this.propertyDealId = propertyDealId;
        this.workspaceId = workspaceId;
    }
}
