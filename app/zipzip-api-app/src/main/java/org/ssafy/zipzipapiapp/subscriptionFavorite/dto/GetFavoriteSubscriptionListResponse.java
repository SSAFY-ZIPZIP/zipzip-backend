package org.ssafy.zipzipapiapp.subscriptionFavorite.dto;

import java.util.List;
import org.ssafy.zipzipapiapp.common.dto.PageMetaDto;
import org.ssafy.zipzipmysqldomain.subscription.dto.SubscriptionFavoriteQueryResponseDto;

public record GetFavoriteSubscriptionListResponse(List<SubscriptionFavoriteQueryResponseDto> content,
                                                  PageMetaDto pageMeta) {
}
