package org.ssafy.zipzipmysqldomain.workspace.repository;

import org.ssafy.zipzipmysqldomain.workspace.entity.Workspace;

public interface WorkspaceRepository {
    Workspace save(Workspace workspace);

    void delete(Long workspaceId);

    void update(String workspaceName, Long workspaceId);
}
