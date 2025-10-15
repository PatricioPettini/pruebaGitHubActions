package com.pato.ejercicio1_validaciones.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CompatibleConMascotasValidator.class)
public @interface CompatibleConMascotas {
    String message() default "Una planta tóxica no puede ser apta para mascotas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
