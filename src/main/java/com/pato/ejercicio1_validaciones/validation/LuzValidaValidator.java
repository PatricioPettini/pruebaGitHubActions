package com.pato.ejercicio1_validaciones.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class LuzValidaValidator implements ConstraintValidator<LuzValida,String> {

    private Set permitidas;

    @Override
    public void initialize(LuzValida ann) {
        this.permitidas = Arrays.stream(ann.value())
                .map(s -> s.toLowerCase(Locale.ROOT))
                .collect(Collectors.toSet());    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return true; // ignora nulos en tests
        return permitidas.contains(value.toLowerCase(Locale.ROOT));
    }
}
