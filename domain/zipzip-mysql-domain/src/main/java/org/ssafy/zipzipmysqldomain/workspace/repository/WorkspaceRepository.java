package org.ssafy.zipzipmysqldomain.workspace.repository;

import java.util.Optional;
import org.ssafy.zipzipmysqldomain.workspace.entity.Workspace;

public interface WorkspaceRepository {
    Workspace save(Workspace workspace);

    Optional<Workspace> findById(Long workspaceId);

    void delete(Long workspaceId);

    void update(String workspaceName, Long workspaceId);

}
