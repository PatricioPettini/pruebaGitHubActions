package com.pato.ejercicio1_validaciones.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FrecuenciaValidaValidator.class)
public @interface FrecuenciaValida {
    // texto o clave i18n del mensaje que verá el cliente cuando falle
    String message() default "{planta.frencuencia.invalida}";

    // elementos estándar de Bean Validation (se usan para escenarios/grupos y metadatos)
    Class[] groups() default {};
    Class[] payload() default {};

    // parámetro de nuestra regla: el conjunto de especies permitidas
    String[] value();
}
