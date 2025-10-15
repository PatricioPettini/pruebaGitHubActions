package com.pato.ejercicio1_validaciones.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pato.ejercicio1_validaciones.model.Planta;
import com.pato.ejercicio1_validaciones.repository.IPlantaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PlantaControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IPlantaRepository plantaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL_CREATE = "/planta/crear"; //SETEAMOS la url del end-point


    @Test
    void crearPlantaTest() throws Exception {

        //ARRANGE
        Planta nuevaPlanta= new Planta();
        nuevaPlanta.setCategoria("exterior");
        nuevaPlanta.setNombreCientifico("Di lotus");
        nuevaPlanta.setNombreComun("lotto");
        nuevaPlanta.setAptaMascota(true);
        nuevaPlanta.setEsToxica(false);
        nuevaPlanta.setStock(2);

        //Act:
        MvcResult mvcResult = mockMvc.perform(
                        post(URL_CREATE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding(StandardCharsets.UTF_8)
                                .content(objectMapper.writeValueAsString(nuevaPlanta))
                )
                //Assert (de la capa web): comprobamos estado + la respuesta en texto
                //comprobamos status code
                .andExpect(status().isOk())
                //comprobamos el tipo de dato y texto de respuesta
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Planta creada!"))
                //recuperamos el resultado completo de la ejecución de la request
                .andReturn();

        //Assert (persistencia/BD)
        //buscamos en DB por campos (no tenemos id en la respuesta, sino un texto plano)
        //vamos a usar prog funcional
        Planta enBD = plantaRepository.findAll().stream()
                .filter(p -> "lotto".equals(p.getNombreComun()))
                .filter(p -> "exterior".equals(p.getCategoria()))
                .filter(p -> "Di lotus".equals(p.getNombreCientifico()))
                .filter(p -> p.getStock()==2)
                .findFirst()
                .orElseThrow(() -> new AssertionError("No se encontró la planta persistida en la DB"));

        // Verificaciones finales
        //Asegurate que el id no sea nulo
        assertThat(enBD.getId()).isNotNull();
    }
}
