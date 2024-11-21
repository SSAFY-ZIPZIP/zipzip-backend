package org.ssafy.zipzipapiapp.workspace.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.zipzipapiapp.common.util.MemberUtil;
import org.ssafy.zipzipapiapp.workspace.dto.GetWorkspaceMemberResponse;
import org.ssafy.zipzipapiapp.workspace.dto.PatchWorkspaceRequest;
import org.ssafy.zipzipapiapp.workspace.dto.PostWorkspaceRequest;
import org.ssafy.zipzipapiapp.workspace.dto.SendWorkspaceInviteRequest;
import org.ssafy.zipzipapiapp.workspace.service.WorkspaceService;

@RestController
@RequestMapping("/v1/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @PostMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> post(@Valid @RequestBody PostWorkspaceRequest postWorkspaceRequest,
                                     Authentication authentication) {
        Long memberId = MemberUtil.getUserId(authentication);
        workspaceService.post(postWorkspaceRequest, memberId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }


    @PatchMapping("/me/{workspaceId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> patch(@PathVariable("workspaceId") Long workspaceId,
                                      @Valid @RequestBody PatchWorkspaceRequest patchWorkspaceRequest,
                                      Authentication authentication) {
        Long memberId = MemberUtil.getUserId(authentication);
        workspaceService.patch(patchWorkspaceRequest, workspaceId, memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/me/{workspaceId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable("workspaceId") Long workspaceId) {
        workspaceService.delete(workspaceId);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/accept-invite")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> acceptInvite(@RequestParam("invite-token") String inviteToken) {
        workspaceService.acceptInvite(inviteToken);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/{workspaceId}/invite")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> sendInvite(@PathVariable("workspaceId") Long workspaceId,
                                           @Valid @RequestBody SendWorkspaceInviteRequest sendWorkspaceInviteRequest) {
        workspaceService.sendInvite(sendWorkspaceInviteRequest, workspaceId);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/{workspaceId}/members")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetWorkspaceMemberResponse>> getWorkspaceMemberListByWorkspaceId(
            @PathVariable("workspaceId") Long workspaceId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(workspaceService.getWorkspaceMemberListByWorkspaceId(workspaceId));
    }
}

