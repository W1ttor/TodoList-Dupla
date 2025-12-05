package com.doido.todolistback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.doido.todolistback.exception.CustomsExceptions.UserNotFoundHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(UserNotFoundHandler.class)
    public ResponseEntity<ApiErrorMenssage> userNotFoundHandler(UserNotFoundHandler exception){
        ApiErrorMenssage threatResponse = new ApiErrorMenssage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    
}
