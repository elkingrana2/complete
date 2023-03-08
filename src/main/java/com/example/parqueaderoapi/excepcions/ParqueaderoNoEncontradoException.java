package com.example.parqueaderoapi.excepcions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParqueaderoNoEncontradoException extends RuntimeException {

    public ParqueaderoNoEncontradoException(Long id) {
        super("No se pudo encontrar al parqueadero con ID: " + id);

    }

}
