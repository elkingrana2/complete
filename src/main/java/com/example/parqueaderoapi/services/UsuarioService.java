package com.example.parqueaderoapi.services;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parqueaderoapi.entities.Parqueadero;
import com.example.parqueaderoapi.entities.Usuario;
import com.example.parqueaderoapi.excepcions.CorreoEnUsoException;
//import com.example.parqueaderoapi.excepcions.NombreEnUsoException;
import com.example.parqueaderoapi.excepcions.UsuarioNoEncontradoException;
import com.example.parqueaderoapi.repositories.ParqueaderoRepository;
import com.example.parqueaderoapi.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        } else {
            throw new UsuarioNoEncontradoException(id);
        }
    }

    public Usuario crearUsuario(Usuario usuario) throws CorreoEnUsoException {

        if (usuarioRepository.findUsuarioByCorreo(usuario.getCorreo()).isPresent()) {
            throw new CorreoEnUsoException(usuario.getCorreo());
        }
        usuario.setRol("socio");
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado)
            throws UsuarioNoEncontradoException, CorreoEnUsoException {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));

        // Si el correo ha cambiado, verificar que no esté en uso
        if (!usuarioActualizado.getCorreo().equals(usuarioExistente.getCorreo()) &&
                usuarioRepository.findUsuarioByCorreo(usuarioActualizado.getCorreo()).isPresent()) {
            throw new CorreoEnUsoException(usuarioActualizado.getCorreo());
        }

        // Actualizar el usuario existente con los nuevos datos
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setRol("socio");
        usuarioExistente.setCorreo(usuarioActualizado.getCorreo());

        // Guardar el usuario actualizado
        Usuario usuarioActualizadoGuardado = usuarioRepository.save(usuarioExistente);

        return usuarioActualizadoGuardado;
    }

    public boolean eliminarUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            throw new UsuarioNoEncontradoException(id);
        }
    }

    // Agregar un parqueadero a un socio

    @Autowired
    private ParqueaderoRepository parqueaderoRepository;

    public void agregarParqueaderoSocio(Long idUsuario, Long idParqueadero) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);

        Usuario usuario = optionalUsuario.get();

        Optional<Parqueadero> optionalParqueadero = parqueaderoRepository.findById(idParqueadero);

        Parqueadero parqueadero = optionalParqueadero.get();

        usuario.addParqueadero(parqueadero);
        usuarioRepository.save(usuario);
        // usuarioRepository.s

    }

}