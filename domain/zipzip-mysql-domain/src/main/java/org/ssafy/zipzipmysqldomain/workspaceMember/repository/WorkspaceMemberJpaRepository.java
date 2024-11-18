package org.ssafy.zipzipmysqldomain.workspaceMember.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.ssafy.zipzipmysqldomain.workspaceMember.entity.WorkspaceMember;

public interface WorkspaceMemberJpaRepository extends CrudRepository<WorkspaceMember, Long> {

    @Modifying
    @Query("DELETE FROM WorkspaceMember wm WHERE wm.workspaceId = :workspaceId AND wm.memberRole != 'OWNER'")
    void deleteAllByWorkspaceIdExceptOwner(@Param("workspaceId") Long workspaceId);


}
