package org.ssafy.zipzipmysqldomain.propertyDeal.repository;

import java.util.List;

public interface PropertydealRepository {
    List<String> findPropertyNameListStartsWithKeyword(String keyword);
}
