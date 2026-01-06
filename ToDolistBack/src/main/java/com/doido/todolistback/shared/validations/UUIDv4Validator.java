package com.doido.todolistback.shared.validations;

import com.doido.todolistback.shared.utils.Regex;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UUIDv4Validator implements ConstraintValidator<UUIDv4, String> {

    private boolean withSpecialChars;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (withSpecialChars) {
            return value.matches(Regex.UUIDv4);
        }
        return value.replaceAll("-", "").length() == 28;
    }

    @Override
    public void initialize(UUIDv4 constraintAnnotation) {
        withSpecialChars  = constraintAnnotation.withSpecialCharacters();
    }
}
