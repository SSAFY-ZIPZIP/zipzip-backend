package org.ssafy.zipzipapiapp.workspace.dto;

import org.ssafy.zipzipmysqldomain.workspaceMember.enums.WorkspaceMemberRole;

public record GetWorkspaceMemberResponse(
        Long memberId,
        String memberNickname,
        WorkspaceMemberRole memberRole
) {
}
