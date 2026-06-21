package com.test.domain.ports.output;

import java.time.LocalDateTime;

import com.test.domain.model.Price;
import com.test.domain.valueobject.BrandId;
import com.test.domain.valueobject.ProductId;

public interface PriceRepository {
    Price findPriceByBrandIdAndProductIdAndApplicationDate(BrandId brandId, ProductId productId,
            LocalDateTime applicationDate);
}
