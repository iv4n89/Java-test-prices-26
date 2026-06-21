package util;

import com.test.valueobject.BrandId;

public final class BrandIdMother {
    public static BrandId create(Long value) {
        return BrandId.create(value);
    }

    public static BrandId random() {
        return create(MotherCreator.random().number().randomNumber());
    }
}
