package util;

import com.test.valueObject.PriceList;

public final class PriceListMother {
    public static PriceList create(Long value) {
        return PriceList.create(value);
    }

    public static PriceList random() {
        return PriceList.create(MotherCreator.random().number().randomNumber());
    }
}
