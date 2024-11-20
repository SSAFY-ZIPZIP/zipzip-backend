package org.ssafy.zipzipmysqldomain.workspace.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.zipzipmysqldomain.workspace.entity.Workspace;

public interface WorkspaceJpaRepository extends CrudRepository<Workspace, Long> {
    Optional<Workspace> findById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Workspace w SET w.name = :workspaceName WHERE w.id = :workspaceId")
    void update(String workspaceName, Long workspaceId);
}
