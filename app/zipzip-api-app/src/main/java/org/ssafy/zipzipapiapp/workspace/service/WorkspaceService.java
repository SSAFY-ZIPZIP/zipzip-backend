package org.ssafy.zipzipapiapp.workspace.service;

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

        memberSerivce.findById(memberId);

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
        String inviteLink = "http://localhost:8080/v1/workspaces/accept-invite?invite-token=" + sendInviteToken;
        
        emailService.sendInvite(sendWorkspaceInviteRequest.email(), inviteLink);
    }

    @Transactional
    public void acceptInvite(String inviteToken) {
        WorkspaceIdAndEmailDto workspaceIdAndEmailDto = jwtTokenProvider.resolveInviteToken(inviteToken);
        Long workspaceId = workspaceIdAndEmailDto.workspaceId();
        String email = workspaceIdAndEmailDto.email();

        workspaceRepository.findByIdOrThrow(workspaceId);
        Long memberId = memberSerivce.findMemberIdByEmail(email);
        workspaceMemberService.save(workspaceId, memberId, WorkspaceMemberRole.MEMBER);
    }

    // 코드 수정 필요 -> 레포지토리에서 throw 하도록 변경하자. 그게 편한듯
    // 서비스 로직이 필요한거면 서비스
}
