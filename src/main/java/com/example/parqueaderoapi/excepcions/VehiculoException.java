package com.example.parqueaderoapi.excepcions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VehiculoException extends RuntimeException {

  public VehiculoException(String mensaje) {
    super(mensaje);
  }

}
