package com.example.parqueaderoapi.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.parqueaderoapi.entities.Parqueadero;
import com.example.parqueaderoapi.entities.Usuario;
import com.example.parqueaderoapi.entities.Vehiculo;
import com.example.parqueaderoapi.services.ParqueaderoService;

import jakarta.validation.Valid;

import com.example.parqueaderoapi.excepcions.*;
import com.example.parqueaderoapi.requests.ParqueaderoRequest;
import com.example.parqueaderoapi.requests.VehiculoRequest;


@RestController
@RequestMapping("/parqueaderos")
public class ParqueaderoController {

    @Autowired
    ParqueaderoService parqueaderoService;

    @GetMapping
    public ResponseEntity<List<Parqueadero>> obtenerParqueaderos() {
        List<Parqueadero> parqueaderos = parqueaderoService.obtenerParqueaderos();
        return ResponseEntity.ok(parqueaderos);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Parqueadero crearParqueadero(@RequestBody @Valid ParqueaderoRequest parqueaderoRequest) {
        Parqueadero nuevParqueadero = parqueaderoService.crearParqueadero(new Parqueadero(parqueaderoRequest));
        return  nuevParqueadero;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parqueadero> obtenerParqueaderoPorId(@PathVariable Long id) {
        Parqueadero parqueadero = parqueaderoService.obtenerParqueaderoPorId(id);
        return ResponseEntity.ok(parqueadero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarParqueadero(@PathVariable Long id) {
        parqueaderoService.eliminarParqueadero(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parqueadero> actualizarParqueadero(@PathVariable Long id,
                                                             @RequestBody Parqueadero parqueadero) {
        Parqueadero parqueaderoActualizado = parqueaderoService.actualizarParqueadero(id, parqueadero);
        return ResponseEntity.ok(parqueaderoActualizado);
    }

    // Ingresar un vehiculo al parqueadero
    @PostMapping(path = "/{parqueaderoId}/vehiculos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String ingresarVehiculo(@PathVariable Long parqueaderoId,
                                                   @RequestBody @Valid VehiculoRequest vehiculoRequest) {

        parqueaderoService.ingresarVehiculo(parqueaderoId, new Vehiculo(vehiculoRequest));
        return ("Vehiculo ingresado correctamente");

    }

    // Registrar salida del parqueadero

    @PutMapping("/{idParqueadero}/vehiculos/{placa}")
    public ResponseEntity<String> registrarSalida(@PathVariable Long idParqueadero, @PathVariable String placa) {
        parqueaderoService.registrarSalida(placa, idParqueadero);
        return ResponseEntity.status(HttpStatus.CREATED).body("Salida registrada correctamente");
    }


    /* 

    // Ver los vehiculos los parqueaderos

    @GetMapping("/vehiculos")
    public ResponseEntity<List<Vehiculo>> obtenerVehivulos() {
        List<Vehiculo> vehiculos = parqueaderoService.obtenerVehiculos();
        return ResponseEntity.ok(vehiculos);
    }*/


    // ver los vehiculos por id de un parqueadero (Los vehiculos que se encuentran
    // en ese parqueadero)

    @GetMapping("/{idParqueadero}/vehiculos")
    public ResponseEntity<List<Vehiculo>> obtenerVehiculosPorParqueadero(@PathVariable Long idParqueadero) {
        List<Vehiculo> vehiculos = parqueaderoService.obtenerVehiculosPorParqueadero(idParqueadero);
        return ResponseEntity.ok(vehiculos);
    }

    // obtener parqueaderos por usuario
    @GetMapping("/usuarios/{idUsuario}")
    public ResponseEntity<List<Parqueadero>> obtenerParqueaderosPorUsuario(@PathVariable Long idUsuario) {
        List<Parqueadero> parqueaderos = parqueaderoService.obtenerParqueaderosPorUsuario(idUsuario);
        return ResponseEntity.ok(parqueaderos);
    }

    // optener detalle de un vehiculo por su placa
    @GetMapping("/vehiculo/{placa}")
    public Vehiculo detalleVehiculo(@PathVariable String placa) {
        return parqueaderoService.detalleVehiculo(placa);
    }


}