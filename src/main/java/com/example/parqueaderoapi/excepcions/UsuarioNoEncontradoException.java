package com.example.parqueaderoapi.excepcions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(Long id) {
        super("No se pudo encontrar al usuario con ID: " + id);
    }
}
