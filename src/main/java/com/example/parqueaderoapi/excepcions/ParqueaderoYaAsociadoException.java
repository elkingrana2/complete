package com.example.parqueaderoapi.excepcions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParqueaderoYaAsociadoException extends RuntimeException {

  public ParqueaderoYaAsociadoException(Long id) {
    super("El parqueadero con ID: " + id + " ya se encuentra asociado a un vehiculo");
  }

}