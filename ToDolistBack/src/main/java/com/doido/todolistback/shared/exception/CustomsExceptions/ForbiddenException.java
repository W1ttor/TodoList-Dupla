package com.doido.todolistback.shared.exception.CustomsExceptions;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(){
        super("Not allowed to perform this operation");
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
