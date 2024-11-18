package org.ssafy.zipzipmysqldomain.workspace.repository;

import org.springframework.data.repository.CrudRepository;
import org.ssafy.zipzipmysqldomain.workspace.entity.Workspace;

public interface WorkspaceJpaRepository extends CrudRepository<Workspace, Long> {

}
