package org.ssafy.zipzipapiapp.propertyDeal.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        // 결과를 반환 객체로 매핑
        return new GetSearchPropertyDealListByLocationResponse(
                propertyDealSearchQueryResponseDtoList.getContent(),
                new PageMetaDto(propertyDealSearchQueryResponseDtoList)
        );


    }
}
