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
import com.example.parqueaderoapi.entities.Historial;
import org.springframework.http.MediaType;
import java.util.Optional;
import com.example.parqueaderoapi.excepcions.*;
import com.example.parqueaderoapi.responses.*;

@RestController
@RequestMapping("/historiales")
public class HistorialController {

  @Autowired
  HistorialService historialService;

  // optener el top 10 de vehiculos que mas ingresan al parqueadero
  @GetMapping(path = "/top10")
  public Optional<Object[]> getTop10PlacasVehiculos() {
    return historialService.getTop10PlacasVehiculos();
  }

  // optener los vehiculos en parqueaderos que estan registrados por primera vez

  @GetMapping(path = "/vehiculosRegistradosPorPrimeraVez")
  public List<Object[]> getVehiculosRegistradosPorPrimeraVez() {
    return historialService.getVehiculosRegistradosPorPrimeraVez();
  }

  // obtener el promedio de uso de un parqueadero por rango de fecha

  // /historial/promedio-uso?parqueaderoId=2&fechaIngreso=2023-03-10T10:00:56.725490&fechaSalida=2023-03-10T11:03:56.725490

  @GetMapping(path = "/promedio-uso")
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

  // optener el promedio de uso de todos los parqueaderos por rango de fecha

  // /historial/promedio-uso-todos-los-parqueaderos?fechaIngreso=2023-03-10T10:00:56.725490&fechaSalida=2023-03-10T11:03:56.725490

  @GetMapping(path = "/promedio-uso-todos-los-parqueaderos")
  public ResponseEntity<Double> obtenerPromedioUsoTodosLosParqueaderos(
      @RequestParam LocalDateTime fechaIngreso, @RequestParam LocalDateTime fechaSalida) {

    Double promedioUso = historialService.obtenerPromedioUsoTodosLosParqueaderos(fechaIngreso,
        fechaSalida);
    if (promedioUso != null) {
      return ResponseEntity.ok(promedioUso);
    } else {
      return ResponseEntity.notFound().build();
    }

  }

  // optener el promedio de tiempo que los vehiculos permanecen en el parqueadero
  @GetMapping(path = "/promedio-tiempo-vehiculos-en-parqueadero")
  public ResponseEntity<Double> obtenerPromedioTiempoVehiculoEnParqueadero(
      @RequestParam Long parqueaderoId) {

    Double promedioTiempo = historialService.obtenerPromedioTiempoVehiculoEnParqueadero(parqueaderoId);
    if (promedioTiempo != null) {
      return ResponseEntity.ok(promedioTiempo);
    } else {
      return ResponseEntity.notFound().build();
    }

  }

  // listado de vehiculos por filtro de letra ordenando por fecha de salida mas
  // reciente
  @GetMapping(path = "/vehiculosPorLetra")
  public List<Historial> getVehiculosPorLetra(@RequestParam Long idParqueadero, LocalDateTime fechaInicio,
      String letra) {

    return historialService.obtenerVehiculosPorFiltro(idParqueadero, fechaInicio, letra);
  }

}
