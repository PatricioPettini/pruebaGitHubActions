package com.pato.ejercicio1_validaciones.service;

import com.pato.ejercicio1_validaciones.model.Planta;

import java.util.List;

public interface IPlantaService {
    Planta getPlanta(Long id);
    List<Planta> getAllPlantas();
    void crearPlanta(Planta planta);
    Planta editarPlanta(Long idPlanta, Planta planta);
    void eliminarPlanta(Long idPlanta);
}