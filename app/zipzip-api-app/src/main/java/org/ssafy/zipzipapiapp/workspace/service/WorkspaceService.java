package org.ssafy.zipzipapiapp.workspace.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.zipzipapiapp.workspace.dto.PatchWorkspaceRequest;
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

    @Transactional
    public void post(PostWorkspaceRequest postWorkspaceRequest, Long memberId) {

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

    @Transactional
    public void patch(PatchWorkspaceRequest patchWorkspaceRequest, Long workspaceId) {
        /*
        workspace의 member를 수정하는 것

        workspace의 이름을 수정하는 것
        - workspace 테이블 변경

        workspace-member 테이블 변경
        - 1. 현재 workspace id 전체 삭제(자신 빼고)
        - 2. 현재 workspace id - member id 만들어서 전체 추가
         */

        List<Long> memberIdList = patchWorkspaceRequest.memberIdList();
        String workspaceName = patchWorkspaceRequest.workspaceName();

        workspaceRepository.update(workspaceName, workspaceId);

        // 2. workspceMemebr 추가하기
        workspaceMemberRepository.deleteAllByWorkspaceIdExceptOwner(workspaceId);

        // 2-1. workspaceMember 추가하기
        List<WorkspaceMember> workspaceMemberList = new ArrayList<>();
        for (Long memberId : memberIdList) {
            workspaceMemberList.add(WorkspaceMember.builder()
                    .workspaceId(workspaceId)
                    .memberId(memberId)
                    .memberRole(WorkspaceMemberRole.MEMBER)
                    .build()
            );
        }
        workspaceMemberRepository.saveAll(workspaceMemberList);


    }

}
