package org.ssafy.zipzipmysqldomain.workspacePropertyDeal.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealQueryResponseDto;
import org.ssafy.zipzipmysqldomain.workspacePropertyDeal.entity.WorkspacePropertyDeal;

public interface WorkspacePropertyDealRepository {

    void save(WorkspacePropertyDeal workspacePropertyDeal);

    Page<PropertyDealQueryResponseDto> findPropertyDealListByWorkspaceId(Long workspaceId, Pageable pageable);

}
