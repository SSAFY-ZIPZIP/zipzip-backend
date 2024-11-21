package org.ssafy.zipzipmysqldomain.workspace.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.zipzipmysqldomain.workspace.dto.GetWorkspaceMemberQueryDto;
import org.ssafy.zipzipmysqldomain.workspace.entity.Workspace;

public interface WorkspaceJpaRepository extends CrudRepository<Workspace, Long> {
    Optional<Workspace> findById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Workspace w SET w.name = :workspaceName WHERE w.id = :workspaceId")
    void update(String workspaceName, Long workspaceId);


    @Query("SELECT new org.ssafy.zipzipmysqldomain.workspace.dto.GetWorkspaceMemberQueryDto(" +
            "wm.memberId, m.nickname, wm.memberRole) " +
            "FROM WorkspaceMember wm " +
            "JOIN Member m ON wm.memberId = m.id " +
            "WHERE wm.workspaceId = :workspaceId")
    List<GetWorkspaceMemberQueryDto> findWorkspaceMemberListByWorkspaceId(@Param("workspaceId") Long workspaceId);
    
}
