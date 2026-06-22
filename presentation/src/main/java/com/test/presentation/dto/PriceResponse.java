package com.test.presentation.dto;

import java.time.LocalDateTime;

import com.test.domain.model.Price;

public record PriceResponse(
        Long priceList,
        Long brandId,
        Long productId,
        String price,
        LocalDateTime startDate,
        LocalDateTime endDate) {

    public static PriceResponse fromDomain(Price price) {
        return new PriceResponse(
                price.getPriceList().getValue(),
                price.getBrandId().getValue(),
                price.getProductId().getValue(),
                price.getPrice().getValue().toPlainString() + " " + price.getCurrency().getValue(),
                price.getStartDate(),
                price.getEndDate());
    }
}
