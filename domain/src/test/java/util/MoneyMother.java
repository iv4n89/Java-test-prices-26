package util;

import java.math.BigDecimal;

import com.test.valueObject.Money;

public final class MoneyMother {
    public static Money create(BigDecimal value) {
        return Money.create(value);
    }

    public static Money random() {
        double randomValue = Math.floor(Math.random() * 1000);
        if (randomValue < 0) {
            randomValue = -randomValue;
        }

        return create(BigDecimal.valueOf(randomValue));
    }

    public static Money zero() {
        return create(BigDecimal.ZERO);
    }

    public static Money one() {
        return create(BigDecimal.ONE);
    }
}
