package com.pato.ejercicio1_validaciones.model;

import com.pato.ejercicio1_validaciones.validation.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@CompatibleConMascotas
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre comun no puede estar vacio")
    @Size(max = 60)
    @Pattern(regexp = "^[\\p{L} .'-]+$",
    message = "solo debe incluir letras/espacios/punto/apóstrofe/guion")
    private String nombreComun;

    @NotBlank(message = "El nombre cientifico no puede estar vacio")
    @Size(max = 80)
    @Pattern(regexp = "^[A-Z][a-z]+\\s[a-z]+$")
    private String nombreCientifico;

    @CategoriaPermitida(
            value = {"interior","exterior","suculenta","arbusto","arbol","flor"},
            message = "La categoria debe ser interior, exterior, suculenta, arbusto, arbol o flor"

    )
    private String categoria;

    @Positive(message = "El precio debe ser positivo")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal precio;

    @PositiveOrZero(message = "El stock no puede ser menor a 0")
    private int stock;

    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaIngreso;

    @LuzValida(
            value = {"baja","media","alta"},
            message = "La luz debe ser baja, media o alta"
    )
    private String luzRequerida;

    @NotNull
    private Boolean aptaMascota;

    @NotNull
    private Boolean esToxica;

    @FrecuenciaValida(
            value = {"diario","semanal","quincenal","mensual"},
            message = "La frecuencia debe ser diario, semanal, quincenal o mensual"

    )
    private String frecuenciaRiego;

}
