package org.ssafy.zipzipmysqldomain.propertyDeal.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PropertyDealSearchQueryResponseDto(
        Long propertyInfoId, // 부동산 정보 ID
        Long propertyDealId, // 부동산 가격 ID
        Long dongcodeId,     // 동코드 ID
        String latitude,        // 위도
        String longitude,       // 경도
        String dealAmount,      // 거래가격
        String sidoName,        // 아파트 주소 - 시도
        String gugunName,       // 아파트 주소 - 구군
        String dongName,        // 아파트 주소 - 동
        LocalDateTime dealDate, // 계약일
        BigDecimal aptSize,         // 전용면적 (Double로 변경)
        String aptName          // 아파트 이름
) {
    @QueryProjection
    public PropertyDealSearchQueryResponseDto {
        // 생성자 로직 추가 필요 시 작성
    }
}
