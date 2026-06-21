package util;

import com.test.valueObject.ProductId;

public final class ProductIdMother {
    public static ProductId create(Long value) {
        return ProductId.create(value);
    }

    public static ProductId random() {
        return ProductId.create(MotherCreator.random().number().randomNumber());
    }
}
