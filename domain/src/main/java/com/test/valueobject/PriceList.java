package com.test.valueobject;

public final class PriceList extends PositiveNumericValueObject<Long> {
    private PriceList(Long value) {
        super(value);
    }

    @Override
    protected String getNullValueErrorMessage() {
        return "PriceList value cannot be null";
    }

    @Override
    protected String getNegativeValueErrorMessage() {
        return "PriceList value cannot be negative";
    }

    public static PriceList create(Long value) {
        return new PriceList(value);
    }
}
