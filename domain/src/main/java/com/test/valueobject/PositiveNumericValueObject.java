package com.test.valueobject;

public abstract class PositiveNumericValueObject<T> extends ValueObject<T> {
    protected PositiveNumericValueObject(T value) {
        super(value);
    }

    protected abstract String getNullValueErrorMessage();

    protected abstract String getNegativeValueErrorMessage();

    @Override
    protected void isValid() {
        this.isNotNull();
        this.isNotNegative();
    }

    protected void isNotNull() {
        if (this.value == null) {
            throw new IllegalArgumentException(this.getNullValueErrorMessage());
        }
    }

    protected void isNotNegative() {
        if (this.value instanceof Long longValue && longValue < 0 ||
                this.value instanceof Integer intValue && intValue < 0 ||
                this.value instanceof Double doubleValue && doubleValue < 0 ||
                this.value instanceof Float floatValue && floatValue < 0) {
            throw new IllegalArgumentException(getNegativeValueErrorMessage());
        }
    }
}
