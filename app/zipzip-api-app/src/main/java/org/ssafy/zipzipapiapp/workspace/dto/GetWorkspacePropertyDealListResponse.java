package org.ssafy.zipzipapiapp.workspace.dto;

import java.util.List;
import org.ssafy.zipzipapiapp.common.dto.PageMetaDto;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealQueryResponseDto;

public record GetWorkspacePropertyDealListResponse(
        List<PropertyDealQueryResponseDto> content,
        PageMetaDto pageMeta
) {
}
