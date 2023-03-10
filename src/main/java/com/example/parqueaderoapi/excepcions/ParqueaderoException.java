package com.example.parqueaderoapi.excepcions;

public class ParqueaderoException extends RuntimeException{
    
    public ParqueaderoException(String mensaje) {
        super(mensaje);
    }
}
