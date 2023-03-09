package com.example.parqueaderoapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parqueaderoapi.services.VehiculoService;
import com.example.parqueaderoapi.entities.Vehiculo;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

  @Autowired
  VehiculoService vehiculoService;

  /*
   * @GetMapping
   * public ResponseEntity<Iterable<Vehiculo>> listadoVehiculos() {
   * 
   * Iterable<Vehiculo> vehiculos = vehiculoService.listadoVehiculos();
   * 
   * return ResponseEntity.ok(vehiculos);
   * }
   */

  /*
   * @GetMapping("/{idParqueadero}")
   * public Optional<Vehiculo> listadoVehiculos(@PathVariable Long idParqueadero)
   * {
   * 
   * Optional<Vehiculo> vehiculos =
   * vehiculoService.listadoVehiculos(idParqueadero);
   * 
   * return vehiculos;
   * }
   * 
   */
}
