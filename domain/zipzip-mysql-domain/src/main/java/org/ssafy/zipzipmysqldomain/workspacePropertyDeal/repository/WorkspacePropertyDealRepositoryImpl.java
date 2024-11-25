package org.ssafy.zipzipmysqldomain.workspacePropertyDeal.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealQueryResponseDto;
import org.ssafy.zipzipmysqldomain.workspacePropertyDeal.entity.WorkspacePropertyDeal;

@Repository
@RequiredArgsConstructor
public class WorkspacePropertyDealRepositoryImpl implements WorkspacePropertyDealRepository {

    private final WorkspacePropertyDealJpaRepository workspacePropertyDealJpaRepository;
    private final WorkspacePropertyDealQueryDslRepository workspacePropertyDealQueryDslRepository;

    @Override
    public void save(WorkspacePropertyDeal workspacePropertyDeal) {
        workspacePropertyDealJpaRepository.save(workspacePropertyDeal);
    }

    @Override
    public Page<PropertyDealQueryResponseDto> findPropertyDealListByWorkspaceId(Long workspaceId, Pageable pageable) {
        return workspacePropertyDealQueryDslRepository.findPropertyDealListByWorkspaceId(workspaceId, pageable);
    }
}
