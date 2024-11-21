package org.ssafy.zipzipmysqldomain.propertyDeal.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PropertydealRepositoryImpl implements PropertydealRepository {

    private final PropertydealJpaRepository propertydealJpaRepository;

    @Override
    public List<String> findPropertyNameListStartsWithKeyword(String keyword) {
        return propertydealJpaRepository.findPropertyNameListStartsWithKeyword(keyword);
    }
}
