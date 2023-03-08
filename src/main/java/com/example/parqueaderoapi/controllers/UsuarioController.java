package com.example.parqueaderoapi.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (NombreEnUsoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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
