package org.ssafy.zipzipapiapp.workspace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PatchWorkspaceRequest(
        @NotBlank(message = "workspace의 이름은 null, \"\". \" \" 이면 안됩니다")
        String workspaceName,
        @NotNull(message = "null값은 허용하지 않습니다. 최소한 빈 리스트를 입력하세요")
        List<Long> memberIdList
) {
}
