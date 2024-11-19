package org.ssafy.zipzipapiapp.subscription.dto;

import java.util.List;
import org.ssafy.zipzipapiapp.common.dto.PageMetaDto;
import org.ssafy.zipzipmysqldomain.subscription.dto.MySubscriptionDto;

public record GetMySubscriptionListResponse(List<MySubscriptionDto> content,
                                            PageMetaDto pageMeta
) {
}

