package com.test.valueobject;

public abstract class StringValueObject extends ValueObject<String> {
    protected StringValueObject(String value) {
        super(value);
    }

    @Override
    protected void isValid() {
        this.isNotNull();
        this.isNotEmpty();
    }

    public abstract String getNullValueErrorMessage();

    public abstract String getEmptyValueErrorMessage();

    protected void isNotNull() {
        if (this.value == null) {
            throw new IllegalArgumentException(getNullValueErrorMessage());
        }
    }

    protected void isNotEmpty() {
        if (this.value.isEmpty()) {
            throw new IllegalArgumentException(getEmptyValueErrorMessage());
        }
    }
}
