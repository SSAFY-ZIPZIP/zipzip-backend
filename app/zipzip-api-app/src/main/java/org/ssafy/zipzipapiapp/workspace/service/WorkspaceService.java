package org.ssafy.zipzipapiapp.workspace.service;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_NOT_FOUND_WORKSPACE;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.zipzipapiapp.common.email.service.EmailService;
import org.ssafy.zipzipapiapp.common.jwt.JwtTokenProvider;
import org.ssafy.zipzipapiapp.member.service.MemberSerivce;
import org.ssafy.zipzipapiapp.workspace.dto.GetWorkspaceMemberResponse;
import org.ssafy.zipzipapiapp.workspace.dto.GetWorkspacePropertyDealListResponse;
import org.ssafy.zipzipapiapp.workspace.dto.GetWorkspaceResponse;
import org.ssafy.zipzipapiapp.workspace.dto.PatchWorkspaceRequest;
import org.ssafy.zipzipapiapp.workspace.dto.PostWorkspacePropertyRequest;
import org.ssafy.zipzipapiapp.workspace.dto.PostWorkspaceRequest;
import org.ssafy.zipzipapiapp.workspace.dto.SendWorkspaceInviteRequest;
import org.ssafy.zipzipapiapp.workspace.dto.WorkspaceIdAndEmailDto;
import org.ssafy.zipzipapiapp.workspaceMember.service.WorkspaceMemberService;
import org.ssafy.zipzipapiapp.workspacePropertyDeal.service.WorkspacePropertyDealService;
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
    private final WorkspacePropertyDealService workspacePropertyDealService;

    @Value("${server.ip}")
    private String serverIp;

    @Value("${server.port}")
    private String serverPort;

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

        String inviteLink = generateInviteLink(sendInviteToken);
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

    @Transactional
    public void delete(Long workspaceId) {
        workspaceRepository.delete(workspaceId);
        workspaceMemberService.deleteAllByWorkspaceId(workspaceId);

    }

    @Transactional
    public void patch(PatchWorkspaceRequest patchWorkspaceRequest, Long workspaceId, Long memberId) {
        List<Long> memberIdList = patchWorkspaceRequest.memberIdList().stream()
                .filter(id -> !id.equals(memberId))
                .collect(Collectors.toList());
        String workspaceName = patchWorkspaceRequest.workspaceName();

        workspaceRepository.update(workspaceName, workspaceId);
        workspaceMemberService.deleteAllByWorkspaceIdExceptOwner(workspaceId);
        workspaceMemberService.saveAll(memberIdList, workspaceId);
    }

    public List<GetWorkspaceMemberResponse> getWorkspaceMemberListByWorkspaceId(Long workspaceId) {
        existByIdOrThrow(workspaceId);
        return workspaceMemberService.getWorkspaceMemberListByWorkspaceId(workspaceId);
    }

    public Workspace findByIdOrThrow(Long workspaceId) {
        return workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new NotFoundException(ERR_NOT_FOUND_WORKSPACE));
    }

    public List<GetWorkspaceResponse> getListByMemberId(Long memberId) {
        return workspaceRepository.findAllByMemberId(memberId).stream()
                .map(getWorkspaceQueryDto -> new GetWorkspaceResponse(getWorkspaceQueryDto.workspaceId(),
                        getWorkspaceQueryDto.workspaceName()))
                .collect(Collectors.toList());
    }


    public Boolean existByIdOrThrow(Long workspaceId) {
        if (workspaceRepository.existsById(workspaceId)) {
            return true;
        }
        throw new NotFoundException(ERR_NOT_FOUND_WORKSPACE);
    }

    public void postWorkspacePropertyDeal(Long id, PostWorkspacePropertyRequest postWorkspacePropertyRequest) {
        workspacePropertyDealService.postWorkspacePropertyDeal(id, postWorkspacePropertyRequest.propertyDealId());
    }

    private String generateInviteLink(String token) {
        return "http://" + serverIp + ":" + serverPort + "/v1/workspaces/accept-invite?invite-token=" + token;
    }


    public GetWorkspacePropertyDealListResponse getPropertyDealListByWorkspaceId(Long id, Pageable pageable) {
        // 리턴값 필요
        return workspacePropertyDealService.getPropertyDealListByWorkspaceId(id, pageable);
    }
}


