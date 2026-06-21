package com.test.domain.util;

import com.test.domain.valueobject.ProductId;

public final class ProductIdMother {
    public static ProductId create(Long value) {
        return ProductId.create(value);
    }

    public static ProductId random() {
        return ProductId.create(MotherCreator.random().number().randomNumber());
    }
}
