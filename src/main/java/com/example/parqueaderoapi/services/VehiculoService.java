package com.example.parqueaderoapi.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.parqueaderoapi.repositories.VehiculoRepository;
import com.example.parqueaderoapi.entities.Vehiculo;
import java.util.Optional;
import com.example.parqueaderoapi.excepcions.VehiculoNoEncontradoException;
import com.example.parqueaderoapi.excepcions.VehiculoException;

@Service
public class VehiculoService {

  private final VehiculoRepository vehiculoRepository;

  @Autowired
  public VehiculoService(VehiculoRepository vehiculoRepository) {
    this.vehiculoRepository = vehiculoRepository;
  }

  // optener detalle de un vehiculo por su placa
  public Vehiculo detalleVehiculo(String placa) {

    if (placa == null) {
      throw new IllegalArgumentException("La placa no puede ser nula");
    }
    if (placa.isEmpty()) {
      throw new IllegalArgumentException("La placa no puede ser vacia");
    }
    Optional<Vehiculo> vehiculo = vehiculoRepository.findByPlaca(placa);

    if (!vehiculo.isPresent()) {
      throw new VehiculoException("El vehiculo con placa " + placa + " no existe");
    }

    return vehiculo.get();

  }

}
