package org.ssafy.zipzipapiapp.propertyDeal.dto;

import java.util.List;
import org.ssafy.zipzipapiapp.common.dto.PageMetaDto;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealSearchQueryResponseDto;

public record GetSearchPropertyDealListByLocationResponse(
        List<PropertyDealSearchQueryResponseDto> content,
        PageMetaDto pageMeta
) {
}
