package org.ssafy.zipzipapiapp.subscription.dto;

import java.util.List;
import org.ssafy.zipzipapiapp.common.dto.PageMetaDto;
import org.ssafy.zipzipmysqldomain.subscription.dto.SubscriptionQueryResponseDto;

public record GetSearchSubscriptionListResponse(List<SubscriptionQueryResponseDto> content,
                                                PageMetaDto pageMeta
) {
}
