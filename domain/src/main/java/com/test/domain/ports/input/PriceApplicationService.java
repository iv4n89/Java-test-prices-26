package com.test.domain.ports.input;

import java.time.LocalDateTime;

import com.test.domain.model.Price;
import com.test.domain.valueobject.BrandId;
import com.test.domain.valueobject.ProductId;

public interface PriceApplicationService {
    Price findPrice(BrandId brandId, ProductId productId, LocalDateTime date);
}
