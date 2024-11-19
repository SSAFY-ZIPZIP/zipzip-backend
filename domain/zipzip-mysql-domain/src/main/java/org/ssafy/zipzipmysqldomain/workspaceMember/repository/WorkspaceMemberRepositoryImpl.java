package org.ssafy.zipzipmysqldomain.workspaceMember.repository;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.workspaceMember.entity.WorkspaceMember;

@Repository
@RequiredArgsConstructor
public class WorkspaceMemberRepositoryImpl implements WorkspaceMemberRepository {

    private final WorkspaceMemberJpaRepository workspaceMemberJpaRepository;

    @Override
    public WorkspaceMember save(WorkspaceMember workspaceMember) {
        return workspaceMemberJpaRepository.save(workspaceMember);
    }

    @Override
    public void deleteAllByWorkspaceId(Long workspaceId) {
        workspaceMemberJpaRepository.deleteAllByWorkspaceId(workspaceId);
    }

    @Override
    public void saveAll(List<WorkspaceMember> workspaceMemberList) {
        workspaceMemberJpaRepository.saveAll(workspaceMemberList);
    }

    @Override
    public void deleteAllByWorkspaceIdExceptOwner(Long workspaceId) {
        workspaceMemberJpaRepository.deleteAllByWorkspaceIdExceptOwner(workspaceId);

    }


}
