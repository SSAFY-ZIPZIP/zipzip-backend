package org.ssafy.zipzipmysqldomain.propertyDeal.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PropertyDealQueryResponseDto(
        Long propertyInfoId,
        Long propertyDealId,
        Long dongcodeId,
        String latitude,
        String longitude,
        String dealAmount,
        String sidoName,
        String gugunName,
        String dongName,
        LocalDateTime dealDate,
        BigDecimal aptSize,
        String aptName
) {
    @QueryProjection
    public PropertyDealQueryResponseDto {

    }
}
