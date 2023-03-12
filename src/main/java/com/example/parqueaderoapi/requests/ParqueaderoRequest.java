package com.example.parqueaderoapi.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParqueaderoRequest {


    @NotBlank
    @NotNull
    private String nombre;
    
    @NotBlank
    @NotNull
    private String direccion;

    @NotNull
    private int capacidad;
    
}
