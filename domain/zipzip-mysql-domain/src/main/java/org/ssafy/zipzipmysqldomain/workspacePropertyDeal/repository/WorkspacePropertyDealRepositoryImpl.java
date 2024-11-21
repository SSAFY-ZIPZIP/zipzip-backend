package org.ssafy.zipzipmysqldomain.workspacePropertyDeal.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.workspacePropertyDeal.entity.WorkspacePropertyDeal;

@Repository
@RequiredArgsConstructor
public class WorkspacePropertyDealRepositoryImpl implements WorkspacePropertyDealRepository {

    private final WorkspacePropertyDealJpaRepository workspacePropertyDealJpaRepository;

    @Override
    public void save(WorkspacePropertyDeal workspacePropertyDeal) {
        workspacePropertyDealJpaRepository.save(workspacePropertyDeal);
    }
}
