package com.example.parqueaderoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parqueaderoapi.entities.Historial;
import java.util.Optional;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long> {

    Optional<Historial> findById(Long id);

    // Optional<Historial> createHistorial(Historial historial);

}