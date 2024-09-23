package com.virtualdressingroom.payment.Exception.handler;

public class CustomPaymentException extends RuntimeException {
    public CustomPaymentException(String message) {
        super(message);
    }

    public CustomPaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}

