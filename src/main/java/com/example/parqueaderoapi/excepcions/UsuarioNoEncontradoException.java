package com.example.parqueaderoapi.excepcions;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(Long id) {
        super("No se pudo encontrar al usuario con ID: " + id);
    }
}

