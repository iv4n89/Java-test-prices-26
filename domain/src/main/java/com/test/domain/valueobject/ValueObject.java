package com.test.domain.valueobject;

import java.util.Objects;

public abstract class ValueObject<T> {
    protected final T value;

    protected ValueObject(T value) {
        this.value = value;
        this.isValid();
    }

    public T getValue() {
        return this.value;
    }

    protected abstract void isValid();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        ValueObject<?> that = (ValueObject<?>) obj;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }
}
