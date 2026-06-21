package com.test.valueObject;

public final class ProductId extends PositiveNumericValueObject<Long> {
    private ProductId(Long value) {
        super(value);
    }

    @Override
    protected String getNullValueErrorMessage() {
        return "ProductId value cannot be null";
    }

    @Override
    protected String getNegativeValueErrorMessage() {
        return "ProductId value cannot be negative";
    }

    public static ProductId create(Long value) {
        return new ProductId(value);
    }
}
