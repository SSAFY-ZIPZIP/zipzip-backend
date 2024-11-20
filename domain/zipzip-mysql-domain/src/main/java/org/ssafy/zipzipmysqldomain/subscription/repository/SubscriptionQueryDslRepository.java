package org.ssafy.zipzipmysqldomain.subscription.repository;

import static org.ssafy.zipzipmysqldomain.subscription.entity.QSubscription.subscription;
import static org.ssafy.zipzipmysqldomain.subscriptionFavorite.entity.QSubscriptionFavorite.subscriptionFavorite;
import static org.ssafy.zipzipmysqldomain.subscriptionProfile.entity.QSubscriptionProfile.subscriptionProfile;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.ssafy.zipzipmysqldomain.subscription.dto.MySubscriptionDto;
import org.ssafy.zipzipmysqldomain.subscription.dto.QMySubscriptionDto;
import org.ssafy.zipzipmysqldomain.subscriptionProfile.dto.QSubscriptionProfileDto;
import org.ssafy.zipzipmysqldomain.subscriptionProfile.dto.SubscriptionProfileDto;

@Repository
public class SubscriptionQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public SubscriptionQueryDslRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Page<MySubscriptionDto> findMyList(Pageable pageable, Long memberId) {
        SubscriptionProfileDto subscriptionProfileDto = queryFactory
                .select(new QSubscriptionProfileDto(
                        subscriptionProfile.memberRegion,
                        subscriptionProfile.memberCategory,
                        subscriptionProfile.isNotificationSubscription))
                .from(subscriptionProfile)
                .where(subscriptionProfile.memberId.eq(memberId))
                .fetchOne();

        List<MySubscriptionDto> content = findMysListContent(pageable, subscriptionProfileDto, memberId);
        JPAQuery<Long> countQuery = findMyListCount(subscriptionProfileDto);
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private List<MySubscriptionDto> findMysListContent(Pageable pageable, SubscriptionProfileDto subscriptionProfileDto,
                                                       Long memberId) {
        return queryFactory
                .select(new QMySubscriptionDto(
                        subscription.id,
                        subscription.deadline,
                        subscription.aptName,
                        subscription.category,
                        subscription.region,
                        subscription.address,
                        subscription.generalHouseHold,
                        subscription.specialHouseHold,
                        subscriptionFavorite.id.isNotNull(),
                        subscription.url
                ))
                .from(subscription)
                .leftJoin(subscriptionFavorite).on(
                        subscriptionFavorite.memberId.eq(memberId),
                        subscriptionFavorite.subscriptionId.eq(subscription.id)
                )
                .where(
                        subscription.category.eq(subscriptionProfileDto.memberCategory()),
                        subscription.region.eq(subscriptionProfileDto.memberRegion())
                )
                .orderBy(subscription.deadline.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private JPAQuery<Long> findMyListCount(SubscriptionProfileDto subscriptionProfileDto) {
        return queryFactory.select(subscription.count())
                .from(subscription)
                .where(
                        subscription.category.eq(subscriptionProfileDto.memberCategory()),
                        subscription.region.eq(subscriptionProfileDto.memberRegion())
                );
    }
}
