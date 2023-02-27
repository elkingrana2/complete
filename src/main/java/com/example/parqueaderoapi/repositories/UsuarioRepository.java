package com.example.parqueaderoapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

import com.example.parqueaderoapi.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    //SELECT * FROM Usuario WHERE correo = ?
    //@Query("SELECT u FROM Usuario u WHERE u.correo = ?1")
    Optional<Usuario> findUsuarioByCorreo(String correo);
    
}
