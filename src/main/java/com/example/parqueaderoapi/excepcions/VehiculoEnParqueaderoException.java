package com.example.parqueaderoapi.excepcions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VehiculoEnParqueaderoException extends RuntimeException {

  public VehiculoEnParqueaderoException(String placa) {
    super("No se puede Registrar Ingreso, ya existe la placa " + placa);
  }
}
