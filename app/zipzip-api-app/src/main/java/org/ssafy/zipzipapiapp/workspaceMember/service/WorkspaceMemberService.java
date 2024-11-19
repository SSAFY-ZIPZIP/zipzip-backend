package org.ssafy.zipzipapiapp.workspaceMember.service;

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
}
