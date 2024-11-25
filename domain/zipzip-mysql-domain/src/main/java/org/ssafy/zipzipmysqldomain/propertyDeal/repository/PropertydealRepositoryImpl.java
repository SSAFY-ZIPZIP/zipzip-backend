package org.ssafy.zipzipmysqldomain.propertyDeal.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealSearchQueryRequestDto;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealSearchQueryResponseDto;

@Repository
@RequiredArgsConstructor
public class PropertydealRepositoryImpl implements PropertydealRepository {

    private final PropertydealJpaRepository propertydealJpaRepository;
    private final PropertyDealQueryDslRepository propertyDealQueryDslRepository;

    @Override
    public List<String> findPropertyNameListStartsWithKeyword(String keyword) {
        return propertydealJpaRepository.findPropertyNameListStartsWithKeyword(keyword);
    }

    @Override
    public Page<PropertyDealSearchQueryResponseDto> findSearchListByLocation(Pageable pageable,
                                                                             PropertyDealSearchQueryRequestDto propertyDealSearchQueryRequestDto) {
        return propertyDealQueryDslRepository.findSearchList(pageable, propertyDealSearchQueryRequestDto);
    }
}