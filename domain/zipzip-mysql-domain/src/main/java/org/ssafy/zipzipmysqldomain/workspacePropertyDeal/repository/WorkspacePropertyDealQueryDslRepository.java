package org.ssafy.zipzipmysqldomain.workspacePropertyDeal.repository;

import static org.ssafy.zipzipmysqldomain.dongCode.entity.QDongCode.dongCode1;
import static org.ssafy.zipzipmysqldomain.propertyDeal.entity.QPropertyDeal.propertyDeal;
import static org.ssafy.zipzipmysqldomain.propertyInfo.entity.QPropertyInfo.propertyInfo;
import static org.ssafy.zipzipmysqldomain.workspacePropertyDeal.entity.QWorkspacePropertyDeal.workspacePropertyDeal;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.PropertyDealQueryResponseDto;
import org.ssafy.zipzipmysqldomain.propertyDeal.dto.QPropertyDealQueryResponseDto;

@Repository
public class WorkspacePropertyDealQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public WorkspacePropertyDealQueryDslRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Page<PropertyDealQueryResponseDto> findPropertyDealListByWorkspaceId(Long workspaceId, Pageable pageable) {
        // Content 조회
        List<PropertyDealQueryResponseDto> content = queryFactory
                .select(new QPropertyDealQueryResponseDto(
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
                .from(workspacePropertyDeal)
                .join(propertyDeal).on(workspacePropertyDeal.propertyDealId.eq(propertyDeal.id))
                .join(propertyInfo).on(propertyDeal.aptSeq.eq(propertyInfo.aptSeq))
                .join(dongCode1).on(dongCode1.dongCode.eq(
                        Expressions.stringTemplate("CONCAT({0}, {1})", propertyInfo.sggCd, propertyInfo.umdCd)
                ))
                .where(workspacePropertyDeal.workspaceId.eq(workspaceId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // Count 조회
        JPAQuery<Long> countQuery = queryFactory
                .select(workspacePropertyDeal.count())
                .from(workspacePropertyDeal)
                .where(workspacePropertyDeal.workspaceId.eq(workspaceId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
