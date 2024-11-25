package org.ssafy.zipzipapiapp.workspacePropertyDeal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ssafy.zipzipapiapp.common.dto.PageMetaDto;
import org.ssafy.zipzipapiapp.workspace.dto.GetWorkspacePropertyDealListResponse;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealQueryResponseDto;
import org.ssafy.zipzipmysqldomain.workspacePropertyDeal.entity.WorkspacePropertyDeal;
import org.ssafy.zipzipmysqldomain.workspacePropertyDeal.repository.WorkspacePropertyDealRepository;

@Service
@RequiredArgsConstructor
public class WorkspacePropertyDealService {
    private final WorkspacePropertyDealRepository workspacePropertyDealRepository;

    public void postWorkspacePropertyDeal(Long workspaceId, Long propertyDealId) {
        WorkspacePropertyDeal workspacePropertyDeal = WorkspacePropertyDeal.builder()
                .workspaceId(workspaceId)
                .propertyDealId(propertyDealId)
                .build();
        workspacePropertyDealRepository.save(workspacePropertyDeal);
    }

    // page -> response로 변환
    public GetWorkspacePropertyDealListResponse getPropertyDealListByWorkspaceId(Long workspaceId, Pageable pageable) {
        Page<PropertyDealQueryResponseDto> propertyDealListByWorkspaceIdPage = workspacePropertyDealRepository.findPropertyDealListByWorkspaceId(
                workspaceId, pageable);

        return new GetWorkspacePropertyDealListResponse(propertyDealListByWorkspaceIdPage.getContent(),
                new PageMetaDto(propertyDealListByWorkspaceIdPage));
    }
}
