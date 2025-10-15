package com.pato.ejercicio1_validaciones.repository;

import com.pato.ejercicio1_validaciones.model.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlantaRepository extends JpaRepository<Planta, Long> {

}
