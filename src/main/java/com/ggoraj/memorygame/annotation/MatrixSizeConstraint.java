package com.ggoraj.memorygame.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MatrixSizeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MatrixSizeConstraint {
    String message() default "invalid matrix's size";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };
}
