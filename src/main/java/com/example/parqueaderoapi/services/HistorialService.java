package com.example.parqueaderoapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parqueaderoapi.repositories.HistorialRepository;
import java.util.List;
import java.time.LocalDateTime;
import com.example.parqueaderoapi.repositories.ParqueaderoRepository;
import java.util.Optional;
import com.example.parqueaderoapi.entities.Parqueadero;
import com.example.parqueaderoapi.excepcions.*;
import com.example.parqueaderoapi.entities.Historial;
import com.example.parqueaderoapi.responses.ErrorResponse;

@Service
public class HistorialService {

  @Autowired
  HistorialRepository historialRepository;

  // optener el top 10 de vehiculos que mas ingresan al parqueadero
  public Optional<Object[]> getTop10PlacasVehiculos() {
    return historialRepository.findTop10PlacasVehiculos();
  }

  // optener los vehiculos en parqueaderos que estan registrados por primera vez
  public List<Object[]> getVehiculosRegistradosPorPrimeraVez() {
    return historialRepository.findVehiculosRegistradosPorPrimeraVez();
  }

  // optener el promedio de uso de un parqueadero por rango de fecha

  public Double obtenerPromedioUso(Long parqueaderoId, LocalDateTime fechaIngreso, LocalDateTime fechaSalida) {

    Optional<Parqueadero> parqueadero = parqueaderoRepository.findById(parqueaderoId);
    if (!parqueadero.isPresent())
      // throw new ParqueaderoNoEncontradoException(parqueaderoId);
      throw new BadRequestException(new ErrorResponse("El parqueadero con id " + parqueaderoId + " no existe."));

    return historialRepository.obtenerPromedioUso(parqueaderoId, fechaIngreso,
        fechaSalida);
  }

  // optener el promedio de uso de todos los parqueaderos por rango de fecha

  public Double obtenerPromedioUsoTodosLosParqueaderos(LocalDateTime fechaIngreso, LocalDateTime fechaSalida) {

    return historialRepository.obtenerPromedioUsoTodosLosParqueaderos(fechaIngreso,
        fechaSalida);
  }

  // optener el promedio de tiempo que los vehiculos permanecen en el parqueadero

  @Autowired
  ParqueaderoRepository parqueaderoRepository;

  public Double obtenerPromedioTiempoVehiculoEnParqueadero(Long parqueaderoId) {

    if (parqueaderoId == null)
      // throw new IllegalArgumentException("El parqueaderoId no puede ser nulo");
      throw new BadRequestException(new ErrorResponse("El parqueadero no puede ser nulo."));

    Optional<Parqueadero> parqueadero = parqueaderoRepository.findById(parqueaderoId);
    if (!parqueadero.isPresent())
      // throw new ParqueaderoNoEncontradoException(parqueaderoId);
      throw new BadRequestException(new ErrorResponse("El parqueadero con id " + parqueaderoId + " no existe."));

    return historialRepository.obtenerPromedioTiempoVehiculoEnParqueadero(parqueaderoId);
  }

  // listado de vehiculos por filtro de letra ordenando por fecha de salida mas
  // reciente
  public List<Historial> obtenerVehiculosPorFiltro(Long parqueaderoId, LocalDateTime fechaInicio, String letra) {

    if (letra == null)
      // throw new IllegalArgumentException("La letra no puede ser nula");
      throw new BadRequestException(new ErrorResponse("La letra no puede ser nula."));

    if (letra == "")
      throw new BadRequestException(new ErrorResponse("La letra no puede ser vacia."));

    Optional<Parqueadero> parqueadero = parqueaderoRepository.findById(parqueaderoId);

    // si el parqueadero no existe
    if (!parqueadero.isPresent()) {

      throw new BadRequestException(new ErrorResponse("El parqueadero con id " + parqueaderoId + " no existe."));
    }

    if (letra.length() > 1)
      throw new BadRequestException(new ErrorResponse("La letra no puede ser mayor a 1 caracter."));

    return historialRepository.obtenerVehiculosPorFiltro(parqueaderoId, fechaInicio, letra);
  }

}
