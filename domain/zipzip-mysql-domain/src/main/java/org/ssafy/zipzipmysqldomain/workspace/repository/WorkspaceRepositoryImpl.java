package org.ssafy.zipzipmysqldomain.workspace.repository;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_NOT_FOUND_WORKSPACE;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipexceptioncommon.exception.NotFoundException;
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
    public Workspace findByIdOrThrow(Long workspaceId) {
        return workspaceJpaRepository.findById(workspaceId)
                .orElseThrow(() -> new NotFoundException(ERR_NOT_FOUND_WORKSPACE));
    }
}
