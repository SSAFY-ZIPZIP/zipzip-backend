package org.ssafy.zipzipmysqldomain.workspaceMember.entity;

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
import org.ssafy.zipzipmysqldomain.workspaceMember.enums.WorkspaceMemberRole;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkspaceMember extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long workspaceId;

    @Column(nullable = false)
    private WorkspaceMemberRole memberRole;

    @Builder
    public WorkspaceMember(Long memberId, Long workspaceId, WorkspaceMemberRole memberRole) {
        this.memberId = memberId;
        this.workspaceId = workspaceId;
        this.memberRole = memberRole;
    }
}
