package com.example.parqueaderoapi.excepcions;

public class NombreEnUsoException extends RuntimeException {
    public NombreEnUsoException(String nombre) {
        super("El nombre " + nombre + " ya está en uso.");
    }
}