package com.example.parqueaderoapi.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoRequest {

    @NotBlank
    @NotNull
    private String placa;

    @NotBlank
    private String modelo;

    @NotBlank
    private String color;
    
}
