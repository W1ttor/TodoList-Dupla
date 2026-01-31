package com.doido.todolistback.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class ApiErrorMenssage {
    
    private HttpStatus httpStatus;
    private String path;
    private List<String> errors;

    public ApiErrorMenssage(HttpStatus httpStatus, String path, String... string){
        super();
        this.httpStatus = httpStatus;
        this.path = path;
        errors = Arrays.asList(string);
    }
}
