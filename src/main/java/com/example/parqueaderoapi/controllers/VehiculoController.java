package com.example.parqueaderoapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.parqueaderoapi.services.VehiculoService;
import com.example.parqueaderoapi.entities.Vehiculo;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

  @Autowired
  VehiculoService vehiculoService;

  // optener detalle de un vehiculo por su placa
  @GetMapping("/{placa}")
  public Vehiculo detalleVehiculo(@PathVariable String placa) {
    return vehiculoService.detalleVehiculo(placa);
  }

}
