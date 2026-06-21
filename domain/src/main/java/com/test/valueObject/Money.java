package com.test.valueObject;

import java.math.BigDecimal;

public final class Money extends ValueObject<BigDecimal> {
    private Money(BigDecimal value) {
        super(value);
    }

    public Money add(Money money) {
        return new Money(value.add(value));
    }

    public Money add(BigDecimal value) {
        return new Money(this.value.add(value));
    }

    public Money substract(Money money) {
        return new Money(value.subtract(money.value));
    }

    public Money substract(BigDecimal value) {
        return new Money(this.value.subtract(value));
    }

    public Money multiply(Money money) {
        return new Money(value.multiply(money.value));
    }

    public Money multiply(BigDecimal value) {
        return new Money(this.value.multiply(value));
    }

    public Money divide(Money money) {
        return new Money(value.divide(money.value));
    }

    public Money divide(BigDecimal value) {
        return new Money(this.value.divide(value));
    }

    public boolean isGreaterThanZero() {
        return this.value.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(Money money) {
        return this.value.compareTo(money.value) > 0;
    }

    public boolean isGreaterThan(BigDecimal value) {
        return this.value.compareTo(value) > 0;
    }

    @Override
    protected void isValid() {
        this.isNotNull();
        this.isNotNegative();
    }

    private void isNotNull() {
        if (value == null) {
            throw new IllegalArgumentException("The value of money cannot be null");
        }
    }

    private void isNotNegative() {
        if (this.isGreaterThanZero()) {
            throw new IllegalArgumentException("The value of money cannot be negative");
        }
    }

    public static Money create(BigDecimal value) {
        return new Money(value);
    }
}
