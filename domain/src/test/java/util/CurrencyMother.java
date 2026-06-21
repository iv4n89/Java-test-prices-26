package util;

import com.test.valueObject.Currency;

public final class CurrencyMother {
    public static Currency create(String value) {
        return Currency.create(value);
    }

    public static Currency random() {
        return create(WordMother.random(3));
    }
}
