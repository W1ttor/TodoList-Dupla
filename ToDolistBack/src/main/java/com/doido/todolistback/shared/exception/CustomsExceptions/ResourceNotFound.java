package com.doido.todolistback.shared.exception.CustomsExceptions;

public class ResourceNotFound extends RuntimeException {
    private static final String MESSAGE = "RecursoNaoEncontrado";

    public ResourceNotFound() {
        this(MESSAGE);
    }

    public ResourceNotFound(String message) {
        super(message);
    }
}
