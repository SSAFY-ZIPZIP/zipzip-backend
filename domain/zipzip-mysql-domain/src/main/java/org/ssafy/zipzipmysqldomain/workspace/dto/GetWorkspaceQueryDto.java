package org.ssafy.zipzipmysqldomain.workspace.dto;

public record GetWorkspaceQueryDto(
        Long workspaceId,
        String workspaceName
) {
}