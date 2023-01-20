package com.ggoraj.memorygame.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MatrixSizeValidator implements ConstraintValidator<MatrixSizeConstraint, Integer> {
    @Override
    public void initialize(MatrixSizeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value % 2 == 0 && value > 3;
    }
}
