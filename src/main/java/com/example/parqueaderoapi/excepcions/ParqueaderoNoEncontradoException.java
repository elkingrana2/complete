package com.example.parqueaderoapi.excepcions;

public class ParqueaderoNoEncontradoException extends RuntimeException{

    public ParqueaderoNoEncontradoException(Long id)
    {
        super("No se pudo encontrar al parqueadero con ID: " + id);

    }
    
}
