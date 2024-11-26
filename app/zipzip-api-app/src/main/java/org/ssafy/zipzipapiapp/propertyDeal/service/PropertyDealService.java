package org.ssafy.zipzipapiapp.propertyDeal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.ssafy.zipzipapiapp.common.dto.PageMetaDto;
import org.ssafy.zipzipapiapp.propertyDeal.dto.GetPropertyNameListResponse;
import org.ssafy.zipzipapiapp.propertyDeal.dto.GetSearchPropertyDealListByLocationResponse;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealSearchQueryRequestDto;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealSearchQueryResponseDto;
import org.ssafy.zipzipmysqldomain.propertyDeal.repository.PropertydealRepository;

@Service
@RequiredArgsConstructor
public class PropertyDealService {

    private final PropertydealRepository propertydealRepository;

    public GetPropertyNameListResponse getPropertyNameListStartsWithKeyword(String keyword) {
        List<String> propertyNameListStartsWithKeyword = propertydealRepository.findPropertyNameListStartsWithKeyword(
                keyword);

        return new GetPropertyNameListResponse(propertyNameListStartsWithKeyword);
    }

    public GetSearchPropertyDealListByLocationResponse getSearchList(Pageable pageable, String sido, String gugun,
                                                                     String dong) {
        PropertyDealSearchQueryRequestDto propertyDealSearchQueryRequestDto = new PropertyDealSearchQueryRequestDto(
                Optional.ofNullable(sido).orElse(null),
                Optional.ofNullable(gugun).orElse(null),
                Optional.ofNullable(dong).orElse(null)
        );

        // 리포지토리 호출
        Page<PropertyDealSearchQueryResponseDto> propertyDealSearchQueryResponseDtoList = propertydealRepository.findSearchListByLocation(
                pageable, propertyDealSearchQueryRequestDto);

        Page<PropertyDealSearchQueryResponseDto> filterPropertyDealSearchQueryResponseDtoList = filterLatestDealsByAptSeq(
                propertyDealSearchQueryResponseDtoList);

        // 결과를 반환 객체로 매핑
        return new GetSearchPropertyDealListByLocationResponse(
                filterPropertyDealSearchQueryResponseDtoList.getContent(),
                new PageMetaDto(filterPropertyDealSearchQueryResponseDtoList)
        );


    }


    public Page<PropertyDealSearchQueryResponseDto> filterLatestDealsByAptSeq(
            Page<PropertyDealSearchQueryResponseDto> propertyDealsPage) {

        // 1. List로 변환
        List<PropertyDealSearchQueryResponseDto> propertyDealsList = propertyDealsPage.getContent();

        // 2. aptSeq별로 최신 데이터 필터링
        Map<String, PropertyDealSearchQueryResponseDto> latestDealsMap = propertyDealsList.stream()
                .collect(Collectors.toMap(
                        PropertyDealSearchQueryResponseDto::aptSeq, // Key: apt_seq
                        deal -> deal,                               // Value: 전체 객체
                        (existing, replacement) -> {
                            // 최신 값을 결정 (dealDate 비교)
                            if (existing.dealDate().compareTo(replacement.dealDate()) > 0) {
                                return existing; // 기존 값 유지
                            } else {
                                return replacement; // 새 값으로 교체
                            }
                        }
                ));

        // 3. 필터링된 결과를 List로 변환
        List<PropertyDealSearchQueryResponseDto> latestDealsList = new ArrayList<>(latestDealsMap.values());

        // 4. List를 다시 Page로 변환
        return PageableExecutionUtils.getPage(
                latestDealsList,
                propertyDealsPage.getPageable(),
                propertyDealsPage::getTotalElements
        );
    }

}
