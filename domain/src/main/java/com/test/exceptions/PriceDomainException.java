package com.test.exceptions;

public final class PriceDomainException extends DomainException {

    public PriceDomainException(String message) {
        super(message);
    }

    public PriceDomainException(String message, Throwable cause) {
        super(message, cause);
    }

}
