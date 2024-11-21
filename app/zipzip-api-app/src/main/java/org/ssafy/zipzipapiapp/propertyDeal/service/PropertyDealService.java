package org.ssafy.zipzipapiapp.propertyDeal.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.zipzipapiapp.propertyDeal.dto.GetPropertyNameListResponse;
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
}
