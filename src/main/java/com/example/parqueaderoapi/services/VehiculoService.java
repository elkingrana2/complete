package com.example.parqueaderoapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parqueaderoapi.repositories.*;
import com.example.parqueaderoapi.entities.*;
import java.util.*;

@Service
public class VehiculoService {

  @Autowired
  VehiculoRepository vehiculoRepository;

  @Autowired
  ParqueaderoRepository parqueaderoRepository;

  // Endpoint GET "Listado de vehículos", devuelve el listado de vehículos que se
  // encuentran actualmente parqueados para un parqueadero en especifico

  /*
   * public Optional<Vehiculo> listadoVehiculosPorId(Long idParqueadero) {
   * 
   * Optional<Parqueadero> parqueadero =
   * parqueaderoRepository.findById(idParqueadero);
   * 
   * return vehiculoRepository.findByParqueadero(parqueadero.get().getId());
   * 
   * }
   * 
   * public List<Vehiculo> listadoVehiculos() {
   * 
   * return vehiculoRepository.findAll();
   * 
   * }
   */

}
