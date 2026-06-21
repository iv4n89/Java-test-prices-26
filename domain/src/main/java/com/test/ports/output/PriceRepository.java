package com.test.ports.output;

import java.time.LocalDateTime;

import com.test.model.Price;
import com.test.valueobject.BrandId;
import com.test.valueobject.ProductId;

public interface PriceRepository {
    Price findPriceByBrandIdAndProductIdAndApplicationDate(BrandId brandId, ProductId productId,
            LocalDateTime applicationDate);
}
