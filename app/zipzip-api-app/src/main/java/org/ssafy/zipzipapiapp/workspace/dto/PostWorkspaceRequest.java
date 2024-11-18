package org.ssafy.zipzipapiapp.workspace.dto;

import jakarta.validation.constraints.NotNull;

public record PostWorkspaceRequest(
        @NotNull(message = "workspace의 이름은 1자 이상 20자 이하입니다") String workspaceName
) {
}
