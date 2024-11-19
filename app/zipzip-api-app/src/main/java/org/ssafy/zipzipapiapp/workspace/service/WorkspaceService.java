package org.ssafy.zipzipapiapp.workspace.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.zipzipapiapp.workspace.dto.PatchWorkspaceRequest;
import org.ssafy.zipzipapiapp.workspace.dto.PostWorkspaceRequest;
import org.ssafy.zipzipapiapp.workspaceMember.service.WorkspaceMemberService;
import org.ssafy.zipzipmysqldomain.workspace.entity.Workspace;
import org.ssafy.zipzipmysqldomain.workspace.repository.WorkspaceRepository;
import org.ssafy.zipzipmysqldomain.workspaceMember.enums.WorkspaceMemberRole;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
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

}
