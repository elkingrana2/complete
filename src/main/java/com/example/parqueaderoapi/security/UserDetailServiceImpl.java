package com.example.parqueaderoapi.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.parqueaderoapi.repositories.UsuarioRepository;
// importar UsernameNotFoundException
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.parqueaderoapi.entities.Usuario;
import com.example.parqueaderoapi.excepcions.BadRequestException;
import com.example.parqueaderoapi.responses.ErrorResponse;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String correo) {

    Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);
    // .orElseThrow(() -> new BadRequestException(new ErrorResponse("El usuario con
    // correo " + correo+ " no existe.")));

    return new UserDetailsImpl(usuario.get());
  }

}
