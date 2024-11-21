package org.ssafy.zipzipmysqldomain.workspace.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.workspace.dto.GetWorkspaceQueryDto;
import org.ssafy.zipzipmysqldomain.workspace.entity.Workspace;

@Repository
@RequiredArgsConstructor
public class WorkspaceRepositoryImpl implements WorkspaceRepository {

    private final WorkspaceJpaRepository workspaceJpaRepository;

    @Override
    public Workspace save(Workspace workspace) {
        return workspaceJpaRepository.save(workspace);
    }

    @Override
    public Optional<Workspace> findById(Long workspaceId) {
        return workspaceJpaRepository.findById(workspaceId);
    }

    @Override
    public void delete(Long workspaceId) {
        workspaceJpaRepository.deleteById(workspaceId);
    }

    @Override
    public void update(String workspaceNsme, Long workspaceId) {
        workspaceJpaRepository.update(workspaceNsme, workspaceId);
    }

    @Override
    public Boolean existsById(Long id) {
        return workspaceJpaRepository.existsById(id);
    }



    @Override
    public List<GetWorkspaceQueryDto> findAllByMemberId(Long memberId) {
        return workspaceJpaRepository.findAllByMemberId(memberId);
    }
}
