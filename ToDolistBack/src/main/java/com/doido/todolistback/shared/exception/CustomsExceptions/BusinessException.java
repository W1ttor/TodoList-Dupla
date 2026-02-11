package com.doido.todolistback.shared.exception.CustomsExceptions;

public class BusinessException extends RuntimeException {

    private static final String MESSAGE = "ExcecaoNegocial";

    public BusinessException() {
        super(MESSAGE);
    }

    public BusinessException(String message) {
        super(message);
    }
}
