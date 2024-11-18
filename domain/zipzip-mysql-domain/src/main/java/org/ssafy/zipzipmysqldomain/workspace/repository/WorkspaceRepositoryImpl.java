package org.ssafy.zipzipmysqldomain.workspace.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.workspace.entity.Workspace;

@Repository
@RequiredArgsConstructor
public class WorkspaceRepositoryImpl implements WorkspaceRepository {

    private final WorkspaceJpaRepository workspaceJpaRepository;

    @Override
    public Workspace save(Workspace workspace) {
        return workspaceJpaRepository.save(workspace);
    }

    @Override
    public void update(String workspaceNsme, Long workspaceId) {
        workspaceJpaRepository.update(workspaceNsme, workspaceId);
    }
}
