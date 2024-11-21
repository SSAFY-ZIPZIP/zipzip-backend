package org.ssafy.zipzipmysqldomain.workspace.repository;

import java.util.List;
import java.util.Optional;
import org.ssafy.zipzipmysqldomain.workspace.dto.GetWorkspaceQueryDto;
import org.ssafy.zipzipmysqldomain.workspace.entity.Workspace;

public interface WorkspaceRepository {
    Workspace save(Workspace workspace);

    Optional<Workspace> findById(Long workspaceId);

    void delete(Long workspaceId);

    void update(String workspaceName, Long workspaceId);

    List<GetWorkspaceQueryDto> findAllByMemberId(Long memberId);

    Boolean existsById(Long id);


}
