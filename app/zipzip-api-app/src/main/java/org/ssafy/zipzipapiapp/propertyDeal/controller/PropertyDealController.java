package org.ssafy.zipzipapiapp.propertyDeal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.zipzipapiapp.propertyDeal.dto.GetPropertyNameListResponse;
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


}
