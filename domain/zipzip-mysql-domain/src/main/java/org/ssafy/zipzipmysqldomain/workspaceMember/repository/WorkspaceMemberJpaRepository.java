package org.ssafy.zipzipmysqldomain.workspaceMember.repository;

import org.springframework.data.repository.CrudRepository;
import org.ssafy.zipzipmysqldomain.workspaceMember.entity.WorkspaceMember;

public interface WorkspaceMemberJpaRepository extends CrudRepository<WorkspaceMember, Long> {
}
