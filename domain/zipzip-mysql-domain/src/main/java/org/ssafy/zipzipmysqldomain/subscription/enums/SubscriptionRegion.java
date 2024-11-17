package org.ssafy.zipzipmysqldomain.subscription.enums;

import static org.ssafy.zipzipexceptioncommon.exception.ErrorMessage.ERR_INVALID_SUBSCRIPTION_REGION;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.ssafy.zipzipexceptioncommon.exception.BadRequestException;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum SubscriptionRegion {
    // 특별시
    SEOUL("서울"),

    // 광역시
    GWANGJU("광주"),
    DAEGU("대구"),
    DAEJEON("대전"),
    BUSAN("부산"),
    SEJONG("세종"),
    ULSAN("울산"),
    INCHEON("인천"),

    // 도
    GANGWON("강원"),
    GYEONGGI("경기"),
    GYEONGNAM("경남"),
    GYEONGBUK("경북"),
    JEONNAM("전남"),
    JEONBUK("전북"),
    JEJU("제주"),
    CHUNGNAM("충남"),
    CHUNGBUK("충북");

    private final String description;

    public static SubscriptionRegion findByDescription(String description) {
        return Arrays.stream(SubscriptionRegion.values())
                .filter(region -> region.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new BadRequestException(ERR_INVALID_SUBSCRIPTION_REGION));
    }
}
