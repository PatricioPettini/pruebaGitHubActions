package com.pato.ejercicio1_validaciones.service;

import com.pato.ejercicio1_validaciones.model.Planta;
import com.pato.ejercicio1_validaciones.repository.IPlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantaService implements IPlantaService {

    @Autowired
    IPlantaRepository plantaRepository;

    @Override
    public Planta getPlanta(Long id) {
        return plantaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Planta> getAllPlantas() {
        return plantaRepository.findAll();
    }

    @Override
    public void crearPlanta(Planta planta) {
        plantaRepository.save(planta);
    }

    @Override
    public Planta editarPlanta(Long idPlanta, Planta planta) {
        Planta plantaExistente=getPlanta(idPlanta);

        plantaExistente.setNombreComun(planta.getNombreComun());
        plantaExistente.setNombreCientifico(planta.getNombreCientifico());
        plantaExistente.setCategoria(planta.getCategoria());
        plantaExistente.setPrecio(planta.getPrecio());
        plantaExistente.setStock(planta.getStock());
        plantaExistente.setFechaIngreso(planta.getFechaIngreso());
        plantaExistente.setLuzRequerida(planta.getLuzRequerida());
        plantaExistente.setAptaMascota(planta.getAptaMascota());
        plantaExistente.setEsToxica(planta.getEsToxica());

        return plantaRepository.save(plantaExistente);

    }

    @Override
    public void eliminarPlanta(Long idPlanta) {
        plantaRepository.deleteById(idPlanta);
    }
}
