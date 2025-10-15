package com.pato.ejercicio1_validaciones.controller;

import com.pato.ejercicio1_validaciones.model.Planta;
import com.pato.ejercicio1_validaciones.service.IPlantaService;
import com.pato.ejercicio1_validaciones.validation.OnCreate;
import com.pato.ejercicio1_validaciones.validation.OnUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planta")
public class PlantaController {

    @Autowired
    IPlantaService plantaService;


    @GetMapping("/get/{idPlanta}")
    public Planta getPlanta(@PathVariable Long idPlanta){
        return plantaService.getPlanta(idPlanta);
    }

    @GetMapping("/get")
    public List<Planta> getPlanta(){
        return plantaService.getAllPlantas();
    }

    @DeleteMapping("/eliminar/{idPlanta}")
    public String eliminarPlanta(@PathVariable Long idPlanta){
        plantaService.eliminarPlanta(idPlanta);
        return "Planta eliminada!";
    }

    @PutMapping("/editar/{idPlanta}")
    public Planta eliminarPlanta(@PathVariable Long idPlanta,@Valid @RequestBody Planta planta){
        return plantaService.editarPlanta(idPlanta, planta);
    }

    @PostMapping("/crear")
    public String eliminarPlanta(@Valid @RequestBody Planta planta){
        plantaService.crearPlanta(planta);
        return "Planta creada!";
    }

}
