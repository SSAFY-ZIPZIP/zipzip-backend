package org.ssafy.zipzipmysqldomain.workspace.dto;

import org.ssafy.zipzipmysqldomain.workspaceMember.enums.WorkspaceMemberRole;

public record GetWorkspaceMemberQueryResponseDto(
        Long memberId,
        String memberNickname,
        WorkspaceMemberRole memberRole
) {
}
