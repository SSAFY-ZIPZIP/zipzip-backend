package org.ssafy.zipzipapiapp.workspace.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.zipzipapiapp.workspace.dto.PostWorkspaceRequest;
import org.ssafy.zipzipmysqldomain.workspace.entity.Workspace;
import org.ssafy.zipzipmysqldomain.workspace.repository.WorkspaceRepository;
import org.ssafy.zipzipmysqldomain.workspaceMember.entity.WorkspaceMember;
import org.ssafy.zipzipmysqldomain.workspaceMember.enums.WorkspaceMemberRole;
import org.ssafy.zipzipmysqldomain.workspaceMember.repository.WorkspaceMemberRepository;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMemberRepository workspaceMemberRepository;

    public void add(PostWorkspaceRequest postWorkspaceRequest, Long memberId) {

        Workspace workspace = Workspace.builder()
                .name(postWorkspaceRequest.workspaceName())
                .build();

        Long savedWorkspaceId = workspaceRepository.save(workspace).getId();

        WorkspaceMember workspaceMember = WorkspaceMember.builder()
                .memberId(memberId)
                .workspaceId(savedWorkspaceId)
                .memberRole(WorkspaceMemberRole.OWNER)
                .build();

        workspaceMemberRepository.save(workspaceMember);
    }

}
