package com.example.parqueaderoapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parqueaderoapi.repositories.HistorialRepository;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class HistorialService {

  @Autowired
  HistorialRepository historialRepository;

  // optener el top 10 de vehiculos que mas ingresan al parqueadero
  public List<Object[]> getTop10PlacasVehiculos() {
    return historialRepository.findTop10PlacasVehiculos();
  }

  // optener los vehiculos en parqueaderos que estan registrados por primera vez
  public List<Object[]> getVehiculosRegistradosPorPrimeraVez() {
    return historialRepository.findVehiculosRegistradosPorPrimeraVez();
  }

  // obtener el promedio de uso de un parqueadero por rango de fecha

  /*
   * public Double obtenerPromedioUso(Long parqueaderoId, LocalDateTime
   * fechaIngreso, LocalDateTime fechaSalida) {
   * return historialRepository.obtenerPromedioUso(parqueaderoId, fechaIngreso,
   * fechaSalida);
   * }
   */

}
