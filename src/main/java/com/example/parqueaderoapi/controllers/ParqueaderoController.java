package com.example.parqueaderoapi.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.parqueaderoapi.entities.Parqueadero;
import com.example.parqueaderoapi.entities.Usuario;
import com.example.parqueaderoapi.entities.Vehiculo;
import com.example.parqueaderoapi.services.ParqueaderoService;
import com.example.parqueaderoapi.excepcions.*;

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

    @PostMapping
    public ResponseEntity<Parqueadero> crearParqueadero(@RequestBody Parqueadero parqueadero) {
        try {
            Parqueadero nuevoParqueadero = parqueaderoService.crearParqueadero(parqueadero);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoParqueadero);
        } catch (NombreEnUsoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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

    /* 
    @PutMapping("/{id}")
    public ResponseEntity<Parqueadero> actualizarParqueadero(@PathVariable Long id, @RequestBody Parqueadero parqueadero) {
        Parqueadero parqueaderoActualizado = parqueaderoService.actualizarParqueadero(id, parqueadero);
        return ResponseEntity.ok(parqueaderoActualizado);
    }
    */

    @PostMapping("/{parqueaderoId}/vehiculos")
    public ResponseEntity<String> ingresarVehiculo(@PathVariable Long parqueaderoId, @RequestBody Vehiculo vehiculoRequest) {
        try {
            parqueaderoService.ingresarVehiculo(vehiculoRequest, parqueaderoId);
            return ResponseEntity.ok().build();
        } catch (ParqueaderoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}


