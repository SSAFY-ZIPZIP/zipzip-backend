package org.ssafy.zipzipmysqldomain.workspaceMybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ssafy.zipzipmysqldomain.workspace.dto.GetWorkspaceQueryDto;

@Mapper
public interface WorkspaceMapper {

//    @Select("SELECT * FROM workspace WHERE id = #{id}")
//    Optional<Workspace> findById(@Param("id") Long id);
//
//    @Update("UPDATE workspace SET name = #{workspaceName} WHERE id = #{workspaceId}")
//    void update(@Param("workspaceName") String workspaceName, @Param("workspaceId") Long workspaceId);

    @Select("""
            SELECT w.id AS workspaceId, w.name AS workspaceName
            FROM workspace w
            JOIN workspace_member wm ON w.id = wm.workspace_id
            WHERE wm.member_id = #{memberId}
            """)
    List<GetWorkspaceQueryDto> findAllByMemberIdWithMyBatis(@Param("memberId") Long memberId);

}
