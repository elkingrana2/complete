package com.example.parqueaderoapi.controllers;

import java.util.*;

import com.example.parqueaderoapi.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;

import com.example.parqueaderoapi.entities.Usuario;
import com.example.parqueaderoapi.excepcions.*;
import com.example.parqueaderoapi.services.UsuarioService;

import jakarta.validation.Valid;

//import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crearUsuario(@RequestBody @Valid UserRequest userRequest) {
    
            Usuario nuevoUsuario = usuarioService.crearUsuario(new Usuario(userRequest));
            return nuevoUsuario;
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // Agregar un parqueadero a un socio
    @PutMapping("/{idUsuario}/parqueaderos/{idParqueadero}")
    public ResponseEntity<String> agregarParqueaderoSocio(@PathVariable Long idUsuario,
            @PathVariable Long idParqueadero) {

        usuarioService.agregarParqueaderoSocio(idUsuario, idParqueadero);

        return ResponseEntity.ok("Parqueadero agregado al socio");

    }

}