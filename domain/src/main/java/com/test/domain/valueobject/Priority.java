package com.test.domain.valueobject;

public final class Priority extends PositiveNumericValueObject<Integer> {
    private Priority(Integer value) {
        super(value);
    }

    @Override
    protected String getNullValueErrorMessage() {
        return "Priority value cannot be null";
    }

    @Override
    protected String getNegativeValueErrorMessage() {
        return "Priority value cannot be negative";
    }

    public static Priority create(Integer value) {
        return new Priority(value);
    }
}
