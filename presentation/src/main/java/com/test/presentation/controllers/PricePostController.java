package com.test.presentation.controllers;

import com.test.application.command.PriceFinderCommand;
import com.test.application.usecases.PriceFinder;
import com.test.domain.model.Price;
import com.test.domain.valueobject.BrandId;
import com.test.domain.valueobject.ProductId;
import com.test.presentation.dto.PriceFinderRequest;
import com.test.presentation.dto.PriceResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/price")
@RequiredArgsConstructor
@RestController
public class PricePostController {
    private final PriceFinder priceFinder;

    @PostMapping("/find")
    public ResponseEntity<PriceResponse> findPrice(@RequestBody PriceFinderRequest request) {
        PriceFinderCommand command = new PriceFinderCommand(
                BrandId.create(request.brandId()),
                ProductId.create(request.productId()),
                request.date());

        Price price = priceFinder.findPrice(command);
        return ResponseEntity.ok(PriceResponse.fromDomain(price));
    }
}
