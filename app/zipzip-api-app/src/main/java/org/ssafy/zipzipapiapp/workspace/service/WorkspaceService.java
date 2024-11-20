package org.ssafy.zipzipapiapp.workspace.service;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_NOT_FOUND_WORKSPACE;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.zipzipapiapp.common.jwt.JwtTokenProvider;
import org.ssafy.zipzipapiapp.member.service.MemberSerivce;
import org.ssafy.zipzipapiapp.workspace.dto.PostWorkspaceRequest;
import org.ssafy.zipzipapiapp.workspace.dto.SendWorkspaceInviteRequest;
import org.ssafy.zipzipapiapp.workspace.email.dto.WorkspaceIdAndEmailDto;
import org.ssafy.zipzipapiapp.workspace.email.service.EmailService;
import org.ssafy.zipzipapiapp.workspaceMember.service.WorkspaceMemberService;
import org.ssafy.zipzipexceptioncommon.exception.NotFoundException;
import org.ssafy.zipzipmysqldomain.workspace.entity.Workspace;
import org.ssafy.zipzipmysqldomain.workspace.repository.WorkspaceRepository;
import org.ssafy.zipzipmysqldomain.workspaceMember.enums.WorkspaceMemberRole;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final MemberSerivce memberSerivce;
    private final EmailService emailService;
    private final JwtTokenProvider jwtTokenProvider;
    private final WorkspaceMemberService workspaceMemberService;

    @Transactional
    public void post(PostWorkspaceRequest postWorkspaceRequest, Long memberId) {
        Workspace workspace = Workspace.builder()
                .name(postWorkspaceRequest.workspaceName())
                .build();

        Long savedWorkspaceId = workspaceRepository.save(workspace).getId();

        workspaceMemberService.save(savedWorkspaceId, memberId, WorkspaceMemberRole.OWNER);
    }

    @Transactional
    public void sendInvite(SendWorkspaceInviteRequest sendWorkspaceInviteRequest, Long workspaceId) {
        // 1. 이메일 전송 시, 전달할 토큰 만들기
        String sendInviteToken = jwtTokenProvider.generateSendInviteToken(workspaceId,
                sendWorkspaceInviteRequest.email());
        // 초대 링크 생성
        String inviteLink = "http://158.247.195.119:8080/v1/workspaces/accept-invite?invite-token=" + sendInviteToken;

        emailService.sendInvite(sendWorkspaceInviteRequest.email(), inviteLink);
    }

    @Transactional
    public void acceptInvite(String inviteToken) {
        WorkspaceIdAndEmailDto workspaceIdAndEmailDto = jwtTokenProvider.resolveInviteToken(inviteToken);
        Long workspaceId = workspaceIdAndEmailDto.workspaceId();
        String email = workspaceIdAndEmailDto.email();

        findByIdOrThrow(workspaceId);
        Long memberId = memberSerivce.findMemberIdByEmailOrThrow(email);
        workspaceMemberService.save(workspaceId, memberId, WorkspaceMemberRole.MEMBER);
    }

    public Workspace findByIdOrThrow(Long workspaceId) {
        return workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new NotFoundException(ERR_NOT_FOUND_WORKSPACE));
    }
}


