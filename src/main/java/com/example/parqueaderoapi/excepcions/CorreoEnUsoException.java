package com.example.parqueaderoapi.excepcions;


public class CorreoEnUsoException extends RuntimeException{

    public CorreoEnUsoException(String correo) {
        super("El correo electrónico " + correo + " ya está en uso.");
    }
}
