package com.ggoraj.memorygame.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MatchRequestValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchRequestConstraint {

    String message() default "invalid parameter";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };

}
