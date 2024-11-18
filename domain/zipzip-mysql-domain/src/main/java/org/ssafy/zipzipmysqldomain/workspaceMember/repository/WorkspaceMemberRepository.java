package org.ssafy.zipzipmysqldomain.workspaceMember.repository;

import java.util.List;
import org.ssafy.zipzipmysqldomain.workspaceMember.entity.WorkspaceMember;

public interface WorkspaceMemberRepository {
    WorkspaceMember save(WorkspaceMember workspaceMember);

    void saveAll(List<WorkspaceMember> workspaceMemberList);

    void deleteAllByWorkspaceIdExceptOwner(Long workspaceMemberId);

}
