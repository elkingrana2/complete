package com.example.parqueaderoapi.excepcions;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VehiculoNoAsignadoException extends RuntimeException {

  public VehiculoNoAsignadoException(String placa) {
    super("El vehiculo con placa: " + placa + "no esta registrado en ningun parqueadero");
  }

}
