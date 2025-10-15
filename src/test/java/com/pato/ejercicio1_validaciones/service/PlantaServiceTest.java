package com.pato.ejercicio1_validaciones.service;

import com.pato.ejercicio1_validaciones.model.Planta;
import com.pato.ejercicio1_validaciones.repository.IPlantaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlantaServiceTest {

    @Mock
    private IPlantaRepository plantaRepository;
    @InjectMocks
    private PlantaService plantaService;

    @Test
    void getPlanta(){

        //ARRANGE
        Planta nuevaPlanta= new Planta();
        nuevaPlanta.setNombreComun("MARI");
        when(plantaRepository.findById(2L)).thenReturn(Optional.of(nuevaPlanta));

        //ACT
        Planta planta=plantaService.getPlanta(2L);

        //ASSERT
        assertNotNull(planta);
        assertEquals("mari",planta.getNombreComun().toLowerCase());
        verify(plantaRepository).findById(2L);
        verifyNoMoreInteractions(plantaRepository);
    }

    @Test
    void savePlanta(){

        //ARRANGE
        Planta planta= new Planta();
        planta.setNombreComun("ejemplo");

        //ACT
        plantaService.crearPlanta(planta);

        //ASSERT
        verify(plantaRepository).save(planta);
        verifyNoMoreInteractions(plantaRepository);

    }

}
