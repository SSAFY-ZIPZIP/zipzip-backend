package org.ssafy.zipzipmysqldomain.propertyDeal.repository;

import static org.ssafy.zipzipmysqldomain.dongCode.entity.QDongCode.dongCode1;
import static org.ssafy.zipzipmysqldomain.propertyDeal.entity.QPropertyDeal.propertyDeal;
import static org.ssafy.zipzipmysqldomain.propertyInfo.entity.QPropertyInfo.propertyInfo;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealSearchQueryRequestDto;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealSearchQueryResponseDto;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.QPropertyDealSearchQueryResponseDto;

@Repository
public class PropertyDealQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public PropertyDealQueryDslRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Page<PropertyDealSearchQueryResponseDto> findSearchList(Pageable pageable,
                                                                   PropertyDealSearchQueryRequestDto searchRequestDto) {
        List<PropertyDealSearchQueryResponseDto> content = findSearchListContent(pageable, searchRequestDto);
        JPAQuery<Long> countQuery = findSearchListCount(searchRequestDto);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private List<PropertyDealSearchQueryResponseDto> findSearchListContent(Pageable pageable,
                                                                           PropertyDealSearchQueryRequestDto propertyDealSearchQueryRequestDto) {
        return queryFactory
                .select(new QPropertyDealSearchQueryResponseDto(
                        propertyInfo.id,
                        propertyDeal.id,
                        dongCode1.id,
                        propertyInfo.latitude,
                        propertyInfo.longitude,
                        propertyDeal.dealAmount,
                        dongCode1.sidoName,
                        dongCode1.gugunName,
                        dongCode1.dongName,
                        Expressions.stringTemplate(
                                "CONCAT({0}, '-', LPAD(CONCAT({1}), 2, '0'), '-', LPAD(CONCAT({2}), 2, '0'))",
                                propertyDeal.dealYear,
                                propertyDeal.dealMonth,
                                propertyDeal.dealDay
                        ),
                        propertyDeal.excluUseAr,
                        propertyInfo.aptNm
                ))
                .from(propertyDeal)
                .join(propertyInfo).on(propertyDeal.aptSeq.eq(propertyInfo.aptSeq))
                .join(dongCode1)
                .on(dongCode1.dongCode.eq(
                        Expressions.stringTemplate("CONCAT({0}, {1})", propertyInfo.sggCd, propertyInfo.umdCd)
                ))

                .where(
                        sidoEq(propertyDealSearchQueryRequestDto.sido()),
                        gugunEq(propertyDealSearchQueryRequestDto.gugun()),
                        dongEq(propertyDealSearchQueryRequestDto.dong())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private JPAQuery<Long> findSearchListCount(PropertyDealSearchQueryRequestDto propertyDealSearchQueryRequestDto) {
        return queryFactory
                .select(propertyDeal.count())
                .from(propertyDeal)
                .join(propertyInfo).on(propertyDeal.aptSeq.eq(propertyInfo.aptSeq))
                .join(dongCode1)
                .on(dongCode1.dongCode.eq(
                        Expressions.stringTemplate("CONCAT({0}, {1})", propertyInfo.sggCd, propertyInfo.umdCd)
                ))
                .where(
                        sidoEq(propertyDealSearchQueryRequestDto.sido()),
                        gugunEq(propertyDealSearchQueryRequestDto.gugun()),
                        dongEq(propertyDealSearchQueryRequestDto.dong())
                );
    }

    private BooleanExpression sidoEq(String sido) {
        return sido != null ? dongCode1.sidoName.eq(sido) : null;
    }

    private BooleanExpression gugunEq(String gugun) {
        return gugun != null ? dongCode1.gugunName.eq(gugun) : null;
    }

    private BooleanExpression dongEq(String dong) {
        return dong != null ? dongCode1.dongName.eq(dong) : null;
    }
}
