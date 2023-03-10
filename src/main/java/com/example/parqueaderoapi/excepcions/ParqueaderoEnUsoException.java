package com.example.parqueaderoapi.excepcions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParqueaderoEnUsoException extends RuntimeException {

  public ParqueaderoEnUsoException(Long id) {
    super("No se puede eliminar el parqueadero con ID: " + id + " porque tiene vehiculos asociados");
  }

}