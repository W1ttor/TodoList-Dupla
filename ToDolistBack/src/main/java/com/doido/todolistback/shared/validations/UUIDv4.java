package com.doido.todolistback.shared.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,  ElementType.PARAMETER})
@Constraint(validatedBy = UUIDv4Validator.class)
public @interface UUIDv4 {
    boolean withSpecialCharacters() default true;
    String message() default "UUIDv4";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
