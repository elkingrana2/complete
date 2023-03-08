package com.example.parqueaderoapi.excepcions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParqueaderoNoAsignadoException extends RuntimeException {

  public ParqueaderoNoAsignadoException(Long id) {
    super("El parqueadero con ID: " + id + " no se ha asignado a ningun usuario");

  }

}
