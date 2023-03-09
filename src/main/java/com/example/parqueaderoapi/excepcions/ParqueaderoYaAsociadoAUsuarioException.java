package com.example.parqueaderoapi.excepcions;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParqueaderoYaAsociadoAUsuarioException extends RuntimeException {

  public ParqueaderoYaAsociadoAUsuarioException(Long id, Long idUsuario) {
    super("El parqueadero con ID: " + id + " ya se encuentra asociado al usuario con ID: " + idUsuario);
  }

}
