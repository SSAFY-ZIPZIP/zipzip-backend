package org.ssafy.zipzipmysqldomain.workspaceMember.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.ssafy.zipzipmysqldomain.workspace.dto.GetWorkspaceMemberQueryResponseDto;
import org.ssafy.zipzipmysqldomain.workspaceMember.entity.WorkspaceMember;

public interface WorkspaceMemberJpaRepository extends CrudRepository<WorkspaceMember, Long> {

    void deleteAllByWorkspaceId(Long workspaceId);

    @Modifying
    @Query("DELETE FROM WorkspaceMember wm WHERE wm.workspaceId = :workspaceId AND wm.memberRole != 'OWNER'")
    void deleteAllByWorkspaceIdExceptOwner(@Param("workspaceId") Long workspaceId);

    @Query("SELECT new org.ssafy.zipzipmysqldomain.workspace.dto.GetWorkspaceMemberQueryResponseDto(" +
            "wm.memberId, m.nickname, wm.memberRole) " +
            "FROM WorkspaceMember wm " +
            "JOIN Member m ON wm.memberId = m.id " +
            "WHERE wm.workspaceId = :workspaceId")
    List<GetWorkspaceMemberQueryResponseDto> findWorkspaceMemberListByWorkspaceId(
            @Param("workspaceId") Long workspaceId);

}
