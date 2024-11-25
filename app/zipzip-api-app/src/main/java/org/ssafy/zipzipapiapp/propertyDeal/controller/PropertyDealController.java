package org.ssafy.zipzipapiapp.propertyDeal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.zipzipapiapp.common.aop.Timer;
import org.ssafy.zipzipapiapp.propertyDeal.dto.GetPropertyNameListResponse;
import org.ssafy.zipzipapiapp.propertyDeal.dto.GetSearchPropertyDealListByLocationResponse;
import org.ssafy.zipzipapiapp.propertyDeal.service.PropertyDealService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/properties/")
public class PropertyDealController {

    private final PropertyDealService propertyDealService;

    @GetMapping("/apt-names")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetPropertyNameListResponse> getPropertyNameListStartsWithKeyword(
            @RequestParam("keyword") String keyword) {
        GetPropertyNameListResponse getPropertyNameListResponse = propertyDealService.getPropertyNameListStartsWithKeyword(
                keyword);

        return ResponseEntity.status(HttpStatus.OK)
                .body(getPropertyNameListResponse);
    }

    @Timer
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetSearchPropertyDealListByLocationResponse> getSearchList(Authentication authentication,
                                                                                     @RequestParam(value = "sido", required = false) String sido,
                                                                                     @RequestParam(value = "gugun", required = false) String gugun,
                                                                                     @RequestParam(value = "dong", required = false) String dong,
                                                                                     Pageable pageable) {
        GetSearchPropertyDealListByLocationResponse getSearchPropertyDealListByLocationResponse = propertyDealService.getSearchList(
                pageable, sido,
                gugun, dong);

        return ResponseEntity.status(HttpStatus.OK)
                .body(getSearchPropertyDealListByLocationResponse);
    }
}
