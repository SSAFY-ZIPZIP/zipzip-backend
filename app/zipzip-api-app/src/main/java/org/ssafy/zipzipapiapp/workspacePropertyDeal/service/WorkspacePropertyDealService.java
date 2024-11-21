package org.ssafy.zipzipapiapp.workspacePropertyDeal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

}
