package com.test.ports.input;

import java.time.LocalDateTime;

import com.test.model.Price;
import com.test.valueobject.BrandId;
import com.test.valueobject.ProductId;

public interface PriceApplicationService {
    Price findPrice(BrandId brandId, ProductId productId, LocalDateTime date);
}
