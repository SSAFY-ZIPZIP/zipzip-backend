package org.ssafy.zipzipapiapp.workspaceMember.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.zipzipmysqldomain.workspaceMember.entity.WorkspaceMember;
import org.ssafy.zipzipmysqldomain.workspaceMember.enums.WorkspaceMemberRole;
import org.ssafy.zipzipmysqldomain.workspaceMember.repository.WorkspaceMemberRepository;

@Service
@RequiredArgsConstructor
public class WorkspaceMemberService {

    private final WorkspaceMemberRepository workspaceMemberRepository;

    public void save(Long workspaceId, Long memberId, WorkspaceMemberRole memberRole) {
        WorkspaceMember workspaceMember = WorkspaceMember.builder()
                .workspaceId(workspaceId)
                .memberId(memberId)
                .memberRole(memberRole)
                .build();

        workspaceMemberRepository.save(workspaceMember);
    }

    public void saveAll(List<Long> memberIdList, Long workspaceId) {
        List<WorkspaceMember> workspaceMemberList = memberIdList.stream()
                .map(memberId -> WorkspaceMember.builder()
                        .workspaceId(workspaceId)
                        .memberId(memberId)
                        .memberRole(WorkspaceMemberRole.MEMBER)
                        .build())
                .collect(Collectors.toList());

        workspaceMemberRepository.saveAll(workspaceMemberList);
    }

    public void deleteAllByWorkspaceIdExceptOwner(Long workspaceId) {
        workspaceMemberRepository.deleteAllByWorkspaceIdExceptOwner(workspaceId);
    }

    public void deleteAllByWorkspaceId(Long workspaceId) {
        workspaceMemberRepository.deleteAllByWorkspaceId(workspaceId);
    }
}
