package com.doido.todolistback.shared.exception;

import com.doido.todolistback.shared.exception.CustomsExceptions.*;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

    private MessageSource messageSource;

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorMenssage> userNotFoundHandler(UserNotFoundException exception){
        ApiErrorMenssage threatResponse = new ApiErrorMenssage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(TaskNotFound.class)
    public ResponseEntity<ApiErrorMenssage> taskNotFoundHandler(TaskNotFound exception){
        ApiErrorMenssage apiErrorMenssage = new ApiErrorMenssage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorMenssage);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiErrorMenssage> wrongCredentialsHandler(InvalidCredentialsException exception){
        ApiErrorMenssage apiErrorMenssage = new ApiErrorMenssage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiErrorMenssage);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiErrorMenssage> userAlreadyExistsHandler(UserAlreadyExistsException exception){
        ApiErrorMenssage apiErrorMenssage = new ApiErrorMenssage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorMenssage);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiErrorMenssage> userAlreadyExistsHandler(ResourceNotFound exception){
        ApiErrorMenssage apiErrorMenssage = new ApiErrorMenssage(HttpStatus.BAD_REQUEST, getMessage(exception.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorMenssage);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorMenssage> userAlreadyExistsHandler(BusinessException exception){
        ApiErrorMenssage apiErrorMenssage = new ApiErrorMenssage(HttpStatus.INTERNAL_SERVER_ERROR, getMessage(exception.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorMenssage);
    }


    private String getMessage(String messageKey, Object... args) {
        return messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
    }
}
