package org.ssafy.zipzipapiapp.workspace.dto;

public record GetWorkspaceMemberResponse(
        Long memberId,
        String memberNickname,
        String memberRole
) {
}
