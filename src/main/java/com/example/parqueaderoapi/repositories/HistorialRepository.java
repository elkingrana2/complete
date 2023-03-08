package com.example.parqueaderoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parqueaderoapi.entities.Historial;

public interface HistorialRepository extends JpaRepository<Historial, Long>{

    Optional<Historial> findByPlaca(String placa);
    
}
