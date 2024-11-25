package org.ssafy.zipzipmysqldomain.propertyDeal.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealSearchQueryRequestDto;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealSearchQueryResponseDto;

public interface PropertydealRepository {
    List<String> findPropertyNameListStartsWithKeyword(String keyword);

    Page<PropertyDealSearchQueryResponseDto> findSearchListByLocation(Pageable pageable,
                                                                      PropertyDealSearchQueryRequestDto propertyDealSearchQueryRequestDto);

}
