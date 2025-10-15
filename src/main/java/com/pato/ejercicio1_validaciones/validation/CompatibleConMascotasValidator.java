package com.pato.ejercicio1_validaciones.validation;

import com.pato.ejercicio1_validaciones.model.Planta;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CompatibleConMascotasValidator implements ConstraintValidator<CompatibleConMascotas, Planta> {

    @Override
    public boolean isValid(Planta planta, ConstraintValidatorContext context) {
        if (planta == null) return true; // evita NPE
        Boolean esToxica = planta.getEsToxica();
        Boolean aptaMascota = planta.getAptaMascota();

        if (esToxica == null || aptaMascota == null)
            return true; // se validan por separado con @NotNull

        // ❌ regla: no puede ser tóxica y apta a la vez
        return !(esToxica && aptaMascota);
    }
}
