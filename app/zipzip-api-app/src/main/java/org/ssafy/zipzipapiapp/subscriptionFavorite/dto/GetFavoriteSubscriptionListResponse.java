package org.ssafy.zipzipapiapp.subscriptionFavorite.dto;

import java.util.List;
import org.ssafy.zipzipapiapp.common.dto.PageMetaDto;
import org.ssafy.zipzipmysqldomain.subscription.dto.FavoriteSubscriptionDto;

public record GetFavoriteSubscriptionListResponse(List<FavoriteSubscriptionDto> content,
                                                  PageMetaDto pageMeta) {
}
