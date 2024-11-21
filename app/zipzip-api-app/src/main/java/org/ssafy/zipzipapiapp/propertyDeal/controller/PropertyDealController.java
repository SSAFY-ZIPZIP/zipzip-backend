package org.ssafy.zipzipapiapp.propertyDeal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.zipzipapiapp.propertyDeal.service.PropertyDealService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/properties/")
public class PropertyDealController {

    private final PropertyDealService propertyDealService;


}
