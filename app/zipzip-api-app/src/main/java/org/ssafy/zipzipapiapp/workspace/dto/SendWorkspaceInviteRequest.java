package org.ssafy.zipzipapiapp.workspace.dto;

import jakarta.validation.constraints.Pattern;

public record SendWorkspaceInviteRequest(
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Invalid email format. Please provide a valid email."
        )
        String email
) {
}
