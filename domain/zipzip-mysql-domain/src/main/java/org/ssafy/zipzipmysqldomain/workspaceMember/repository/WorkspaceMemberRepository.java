package org.ssafy.zipzipmysqldomain.workspaceMember.repository;

import java.util.List;
import org.ssafy.zipzipmysqldomain.workspace.dto.GetWorkspaceMemberQueryResponseDto;
import org.ssafy.zipzipmysqldomain.workspaceMember.entity.WorkspaceMember;

public interface WorkspaceMemberRepository {
    WorkspaceMember save(WorkspaceMember workspaceMember);

    void deleteAllByWorkspaceId(Long workspaceId);

    void saveAll(List<WorkspaceMember> workspaceMemberList);

    void deleteAllByWorkspaceIdExceptOwner(Long workspaceMemberId);

    List<GetWorkspaceMemberQueryResponseDto> findWorkspaceMeberListByWorkspaceId(Long workspaceId);

}
