package org.ssafy.zipzipapiapp.subscription.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ssafy.zipzipapiapp.common.dto.PageMetaDto;
import org.ssafy.zipzipapiapp.subscription.dto.GetMySubscriptionListResponse;
import org.ssafy.zipzipapiapp.subscription.dto.GetSearchSubscriptionListResponse;
import org.ssafy.zipzipapiapp.subscriptionFavorite.dto.GetFavoriteSubscriptionListResponse;
import org.ssafy.zipzipapiapp.subscriptionFavorite.service.SubscriptionFavoriteService;
import org.ssafy.zipzipapiapp.subscriptionProfile.dto.GetSubscriptionProfileResponse;
import org.ssafy.zipzipapiapp.subscriptionProfile.dto.PatchSubscriptionProfileRequest;
import org.ssafy.zipzipapiapp.subscriptionProfile.dto.PostSubscriptionProfileRequest;
import org.ssafy.zipzipapiapp.subscriptionProfile.service.SubscriptionProfileService;
import org.ssafy.zipzipmysqldomain.subscription.dto.SubscriptionFavoriteQueryResponseDto;
import org.ssafy.zipzipmysqldomain.subscription.dto.SubscriptionQueryResponseDto;
import org.ssafy.zipzipmysqldomain.subscription.dto.SubscriptionSearchQueryRequestDto;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionCategory;
import org.ssafy.zipzipmysqldomain.subscription.enums.SubscriptionRegion;
import org.ssafy.zipzipmysqldomain.subscription.repository.SubscriptionRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionProfileService subscriptionProfileService;
    private final SubscriptionFavoriteService subscriptionFavoriteService;

    public GetMySubscriptionListResponse getMyList(Pageable pageable, Long memberId) {
        Page<SubscriptionQueryResponseDto> mySubscriptionListPage = subscriptionRepository.findMyList(pageable,
                memberId);
        return new GetMySubscriptionListResponse(mySubscriptionListPage.getContent(),
                new PageMetaDto(mySubscriptionListPage));
    }

    public GetSearchSubscriptionListResponse getSearchList(Pageable pageable, String aptName, String category,
                                                           String region, Long memberId) {
        SubscriptionSearchQueryRequestDto subscriptionSearchQueryRequestDto = new SubscriptionSearchQueryRequestDto(
                aptName,
                // 쿼리 파라미터로 값이 들어오지 않을 수도 있으므로 (null일 수 있으므로) Optional로 처리
                Optional.ofNullable(category)
                        .map(SubscriptionCategory::findByDescription)
                        .orElse(null),
                Optional.ofNullable(region)
                        .map(SubscriptionRegion::findByDescription)
                        .orElse(null)
        );
        Page<SubscriptionQueryResponseDto> searchSubscriptionListPage = subscriptionRepository.findSearchList(pageable,
                subscriptionSearchQueryRequestDto, memberId);
        return new GetSearchSubscriptionListResponse(searchSubscriptionListPage.getContent(),
                new PageMetaDto(searchSubscriptionListPage));
    }

    public GetFavoriteSubscriptionListResponse getMyFavoriteList(Pageable pageable, Long memberId) {
        Page<SubscriptionFavoriteQueryResponseDto> favoriteSubscriptionDtoPage = subscriptionRepository.findMyFavoriteList(
                pageable,
                memberId);
        return new GetFavoriteSubscriptionListResponse(favoriteSubscriptionDtoPage.getContent(),
                new PageMetaDto(favoriteSubscriptionDtoPage));
    }

    public void postProfile(PostSubscriptionProfileRequest postSubscriptionProfileRequest,
                            Long memberId) {
        subscriptionProfileService.post(postSubscriptionProfileRequest, memberId);
    }

    public Optional<GetSubscriptionProfileResponse> getProfile(Long memberId) {
        return subscriptionProfileService.get(memberId);
    }

    public void patchProfile(PatchSubscriptionProfileRequest patchSubscriptionProfileRequest,
                             Long memberId) {
        subscriptionProfileService.patch(patchSubscriptionProfileRequest, memberId);
    }

    public void postFavorite(Long memberId, Long subscriptionId) {
        subscriptionFavoriteService.post(memberId, subscriptionId);
    }

    public void deleteFavorite(Long memberId, Long subscriptionId) {
        subscriptionFavoriteService.delete(memberId, subscriptionId);
    }
}
