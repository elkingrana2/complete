package com.example.parqueaderoapi.repositories;

import java.util.Optional;
import java.util.Stack;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parqueaderoapi.entities.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long>{

    Optional<Vehiculo> findByPlaca(String placa);
    //Stack<Vehiculo> findByPlaca(String placa);
}