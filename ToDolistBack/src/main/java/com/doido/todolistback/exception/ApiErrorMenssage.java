package com.doido.todolistback.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ApiErrorMenssage {
    
    private HttpStatus httpStatus;
    private List<String> errors;

    public ApiErrorMenssage(HttpStatus httpStatus, String string){
        super();
        this.httpStatus = httpStatus;
        errors = Arrays.asList(string);
    }
}
