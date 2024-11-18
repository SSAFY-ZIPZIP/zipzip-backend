package org.ssafy.zipzipapiapp.workspace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostWorkspaceRequest(
        @NotBlank(message = "workspace의 이름은 null, \"\". \" \" 이면 안됩니다") @Size(min = 1, max = 20, message = "workspace의 이름은 1자 이상 20자 이하입니다") String workspaceName
) {
}
