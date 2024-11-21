package org.ssafy.zipzipmysqldomain.propertyDeal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.ssafy.zipzipmysqldomain.propertyDeal.entity.PropertyDeal;

public interface PropertydealJpaRepository extends CrudRepository<PropertyDeal, Long> {
    // 쿼리짜기
    @Query("SELECT DISTINCT pd.aptDong FROM PropertyDeal pd WHERE pd.aptDong LIKE CONCAT(:keyword, '%')")
    List<String> findPropertyNameListStartsWithKeyword(@Param("keyword") String keyword);

}
