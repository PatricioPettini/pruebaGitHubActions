package com.pato.ejercicio1_validaciones.controller;

import com.pato.ejercicio1_validaciones.model.Planta;
import com.pato.ejercicio1_validaciones.repository.IPlantaRepository;
import com.pato.ejercicio1_validaciones.service.IPlantaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PlantaController.class)
public class PlantaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IPlantaService plantaService;

    @Test
    void getPlantas() throws Exception {

        //ARRANGE
        Planta planta= new Planta();
        planta.setCategoria("arbol");

        when(plantaService.getAllPlantas()).thenReturn(List.of(planta));

        //ACT
        mvc.perform(get("/planta/get").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$[0].categoria").value("arbol"));
        //ASSERT

    }

    @Test
    void editarPlanta() throws Exception {
        // ARRANGE
        Long id = 2L;
        Planta plantaExistente = new Planta();
        plantaExistente.setId(id);
        plantaExistente.setNombreComun("MARI");

        Planta plantaEditada = new Planta();
        plantaEditada.setId(id);
        plantaEditada.setNombreComun("LAVANDA");

        when(plantaService.editarPlanta(eq(id), any(Planta.class))).thenReturn(plantaEditada);

        // ACT & ASSERT
        mvc.perform(put("/planta/editar/{idPlanta}", id)
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content("""
            {
                "nombreComun": "LAVANDA",
                "nombreCientifico": "Lavandula officinalis",
                "aptaMascota": true,
                "esToxica": false
            }
        """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("$.nombreComun").value("LAVANDA"));

        verify(plantaService).editarPlanta(eq(id), any(Planta.class));
    }

}
