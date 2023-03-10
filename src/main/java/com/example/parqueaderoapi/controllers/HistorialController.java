package com.example.parqueaderoapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.parqueaderoapi.services.HistorialService;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/historiales")
public class HistorialController {

  @Autowired
  HistorialService historialService;

  // optener el top 10 de vehiculos que mas ingresan al parqueadero
  @GetMapping("/top10")
  public List<Object[]> getTop10PlacasVehiculos() {
    return historialService.getTop10PlacasVehiculos();
  }

  // optener los vehiculos en parqueaderos que estan registrados por primera vez

  @GetMapping("/vehiculosRegistradosPorPrimeraVez")
  public List<Object[]> getVehiculosRegistradosPorPrimeraVez() {
    return historialService.getVehiculosRegistradosPorPrimeraVez();
  }

  // obtener el promedio de uso de un parqueadero por rango de fecha

  // /historial/promedio-uso?parqueaderoId=2&fechaIngreso=2023-03-10T10:00:56.725490&fechaSalida=2023-03-10T11:03:56.725490

  @GetMapping("/promedio-uso")
  public ResponseEntity<Double> obtenerPromedioUso(@RequestParam Long parqueaderoId,

      @RequestParam LocalDateTime fechaIngreso, @RequestParam LocalDateTime fechaSalida) {

    Double promedioUso = historialService.obtenerPromedioUso(parqueaderoId,
        fechaIngreso, fechaSalida);
    if (promedioUso != null) {
      return ResponseEntity.ok(promedioUso);
    } else {
      return ResponseEntity.notFound().build();
    }

  }

}
