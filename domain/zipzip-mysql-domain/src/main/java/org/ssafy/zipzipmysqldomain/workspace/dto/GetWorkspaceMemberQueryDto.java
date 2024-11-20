package org.ssafy.zipzipmysqldomain.workspace.dto;

import org.ssafy.zipzipmysqldomain.workspaceMember.enums.WorkspaceMemberRole;

public record GetWorkspaceMemberQueryDto(
        Long memberId,
        String memberNickname,
        WorkspaceMemberRole memberRole
) {
}
