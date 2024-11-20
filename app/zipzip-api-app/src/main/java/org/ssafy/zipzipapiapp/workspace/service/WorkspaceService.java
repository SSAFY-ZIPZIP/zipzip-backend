package org.ssafy.zipzipapiapp.workspace.service;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_NOT_FOUND_WORKSPACE;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.zipzipapiapp.common.email.service.EmailService;
import org.ssafy.zipzipapiapp.common.jwt.JwtTokenProvider;
import org.ssafy.zipzipapiapp.member.service.MemberSerivce;
import org.ssafy.zipzipapiapp.workspace.dto.PostWorkspaceRequest;
import org.ssafy.zipzipapiapp.workspace.dto.SendWorkspaceInviteRequest;
import org.ssafy.zipzipapiapp.workspace.dto.WorkspaceIdAndEmailDto;
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

    @Value("${server.ip}")
    private static String serverIp;

    @Value("${server.port}")
    private static String serverPort;

    private static final String INVITE_LINK =
            "http://" + serverIp + ":" + serverPort + "/v1/workspaces/accept-invite?invite-token=";

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

        emailService.sendInvite(sendWorkspaceInviteRequest.email(), INVITE_LINK + sendInviteToken);
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


