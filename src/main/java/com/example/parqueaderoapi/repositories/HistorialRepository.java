package com.example.parqueaderoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parqueaderoapi.entities.Historial;
import java.util.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long> {

    Optional<Historial> findById(Long id);

    // optener el top 10 de vehiculos que mas ingresan al parqueadero
    @Query(value = "SELECT placa_vehiculo, COUNT(*) as veces_registrado FROM historial GROUP BY placa_vehiculo ORDER BY veces_registrado DESC LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10PlacasVehiculos();

    // Optional<Historial> createHistorial(Historial historial);

    // verificar de los vehículos parqueados en todos los parqueaderos cuales son
    // por primera vez y cuales no

    // vehiculos registrados en el parqueadero por primera vez
    @Query(value = "SELECT * FROM vehiculo WHERE placa NOT IN (SELECT placa_vehiculo FROM historial)", nativeQuery = true)
    List<Object[]> findVehiculosRegistradosPorPrimeraVez();

    // obtener el promedio de uso de un parqueadero por rango de fecha

    @Query(value = "SELECT AVG(h.duracion_segundos) FROM historial h WHERE h.parqueadero_id = :parqueaderoId AND h.fecha_ingreso >= :fechaIngreso AND h.fecha_salida <= :fechaSalida", nativeQuery = true)

    Double obtenerPromedioUso(@Param("parqueaderoId") Long parqueaderoId,
            @Param("fechaIngreso") LocalDateTime fechaIngreso, @Param("fechaSalida") LocalDateTime fechaSalida);

    // optener el promedio de uso de todos los parqueaderos por rango de fecha
    @Query(value = "SELECT AVG(h.duracion_segundos) FROM historial h WHERE h.fecha_ingreso >= :fechaIngreso AND h.fecha_salida <= :fechaSalida", nativeQuery = true)
    Double obtenerPromedioUsoTodosLosParqueaderos(@Param("fechaIngreso") LocalDateTime fechaIngreso,
            @Param("fechaSalida") LocalDateTime fechaSalida);

    // optener el promedio de tiempo que los vehiculos permanecen en el parqueadero
    @Query(value = "SELECT AVG(h.duracion_segundos) AS promedio_tiempo FROM historial h WHERE parqueadero_id = :parqueaderoId", nativeQuery = true)
    Double obtenerPromedioTiempoVehiculoEnParqueadero(@Param("parqueaderoId") Long parqueaderoId);

    // listado de vehiculos por filtro de letra ordenando por fecha de salida mas
    // reciente
    @Query(value = "SELECT * FROM historial WHERE parqueadero_id = :parqueaderoId AND fecha_ingreso >= :fechaInicio AND placa_vehiculo LIKE %:letra% ORDER BY fecha_salida DESC", nativeQuery = true)
    List<Historial> obtenerVehiculosPorFiltro(@Param("parqueaderoId") Long parqueaderoId,
            @Param("fechaInicio") LocalDateTime fechaInicio, @Param("letra") String letra);

}