package com.test.application.command;

import java.time.LocalDateTime;

import com.test.domain.valueobject.BrandId;
import com.test.domain.valueobject.ProductId;

public record PriceFinderCommand(
        BrandId brandId,
        ProductId productId,
        LocalDateTime date) {
}
