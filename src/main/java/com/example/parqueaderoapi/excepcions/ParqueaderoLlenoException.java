package com.example.parqueaderoapi.excepcions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParqueaderoLlenoException extends RuntimeException {

  public ParqueaderoLlenoException(Long id) {
    super("El parqueadero con ID: " + id + " se encuentra lleno");

  }

}
