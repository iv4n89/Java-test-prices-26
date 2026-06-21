package com.test.valueObject;

public final class BrandId extends PositiveNumericValueObject<Long> {
    private BrandId(Long value) {
        super(value);
    }

    @Override
    protected String getNullValueErrorMessage() {
        return "BrandId value cannot be null";
    }

    @Override
    protected String getNegativeValueErrorMessage() {
        return "BrandId value cannot be negative";
    }
}
