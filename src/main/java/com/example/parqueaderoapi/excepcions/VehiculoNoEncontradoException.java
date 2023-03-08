package com.example.parqueaderoapi.excepcions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VehiculoNoEncontradoException extends RuntimeException {

  public VehiculoNoEncontradoException(String placa) {
    super("No se puede Registrar Salida, no existe la placa " + placa);
  }

}
