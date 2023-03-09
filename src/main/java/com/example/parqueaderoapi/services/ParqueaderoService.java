package com.example.parqueaderoapi.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parqueaderoapi.entities.Historial;
import com.example.parqueaderoapi.entities.Parqueadero;
import com.example.parqueaderoapi.entities.Usuario;
import com.example.parqueaderoapi.entities.Vehiculo;
import com.example.parqueaderoapi.excepcions.NombreEnUsoException;
import com.example.parqueaderoapi.excepcions.ParqueaderoNoEncontradoException;
import com.example.parqueaderoapi.excepcions.VehiculoEnParqueaderoException;
import com.example.parqueaderoapi.excepcions.VehiculoNoEncontradoException;
import com.example.parqueaderoapi.excepcions.ParqueaderoEnUsoException;
import com.example.parqueaderoapi.excepcions.ParqueaderoNoAsignadoException;
import com.example.parqueaderoapi.excepcions.VehiculoNoAsignadoException;
import com.example.parqueaderoapi.excepcions.UsuarioNoEncontradoException;
import com.example.parqueaderoapi.excepcions.ParqueaderoException;
import com.example.parqueaderoapi.repositories.HistorialRepository;
import com.example.parqueaderoapi.repositories.ParqueaderoRepository;
import com.example.parqueaderoapi.repositories.UsuarioRepository;
import com.example.parqueaderoapi.repositories.VehiculoRepository;

import jakarta.transaction.Transactional;

@Service
public class ParqueaderoService {
    @Autowired
    private ParqueaderoRepository parqueaderoRepository;

    public List<Parqueadero> obtenerParqueaderos() {
        return parqueaderoRepository.findAll();
    }

    public Parqueadero crearParqueadero(Parqueadero parqueadero) throws NombreEnUsoException {
        if (parqueaderoRepository.findByNombre(parqueadero.getNombre()).isPresent()) {
            throw new NombreEnUsoException(parqueadero.getNombre());
        }
        parqueadero.setEspacioDisponible(parqueadero.getCapacidad());
        return parqueaderoRepository.save(parqueadero);
    }

    public Parqueadero obtenerParqueaderoPorId(Long id) {
        Optional<Parqueadero> parqueaderoOptional = parqueaderoRepository.findById(id);
        if (parqueaderoOptional.isPresent()) {
            return parqueaderoOptional.get();
        } else {
            throw new ParqueaderoNoEncontradoException(id);
        }
    }

    public Parqueadero actualizarParqueadero(Long id, Parqueadero parqueaderoActualizado) {
        Optional<Parqueadero> parqueadero = parqueaderoRepository.findById(id);
        if (parqueadero.isPresent()) {
            // parqueaderoActualizado.setId(id);
            // parqueaderoRepository.save(parqueaderoActualizado);

            parqueadero.get().setNombre(parqueaderoActualizado.getNombre());
            parqueadero.get().setCapacidad(parqueaderoActualizado.getCapacidad());
            parqueadero.get().setDireccion(parqueaderoActualizado.getDireccion());
        }
        // parqueaderoActualizado.setEspacioDisponible(
        // parqueaderoActualizado.getCapacidad() -
        // parqueaderoActualizado.getVehiculos().size());
        parqueadero.get()
                .setEspacioDisponible(parqueaderoActualizado.getCapacidad() - parqueadero.get().getVehiculos().size());
        Parqueadero parqueaderoActualizadoGuardado = parqueaderoRepository.save(parqueadero.get());

        return parqueaderoActualizadoGuardado;
    }

    public boolean eliminarParqueadero(Long id) {
        Optional<Parqueadero> parqueaderoOptional = parqueaderoRepository.findById(id);

        if (parqueaderoOptional.get().getVehiculos().isEmpty()) {
            throw new ParqueaderoEnUsoException(id);
        }

        if (parqueaderoOptional.isPresent()) {
            parqueaderoRepository.deleteById(id);
            return true;
        } else {
            throw new ParqueaderoNoEncontradoException(id);
        }
    }

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public void ingresarVehiculo(Long parqueaderoId, Vehiculo vehiculoRequest) {

        Optional<Parqueadero> optionalParqueadero = parqueaderoRepository.findById(parqueaderoId);

        if (optionalParqueadero.get().getUsuario() == null) {
            throw new ParqueaderoNoAsignadoException(parqueaderoId);
        }

        if (!optionalParqueadero.isPresent()) {
            throw new ParqueaderoNoEncontradoException(parqueaderoId);
        }

        Optional<Vehiculo> optionalVehiculo = vehiculoRepository.findByPlaca(vehiculoRequest.getPlaca());

        if (optionalVehiculo.isPresent() && optionalVehiculo.get().getParqueadero() != null) {
            throw new VehiculoEnParqueaderoException(vehiculoRequest.getPlaca());
        }

        if (optionalVehiculo.isPresent()) {
            Parqueadero parqueadero = optionalParqueadero.get();
            Vehiculo vehiculo = optionalVehiculo.get();

            vehiculo.setFechaIngreso(LocalDateTime.now());
            parqueadero.addVehiculo(vehiculo);
            parqueaderoRepository.save(parqueadero);
            vehiculoRepository.save(vehiculo);
        } else {

            Parqueadero parqueadero = optionalParqueadero.get();

            Vehiculo vehiculo;

            vehiculo = vehiculoRequest;

            vehiculo.setFechaIngreso(LocalDateTime.now());

            parqueadero.addVehiculo(vehiculo);
            parqueaderoRepository.save(parqueadero);
            vehiculoRepository.save(vehiculo);

        }

    }

    @Autowired
    private HistorialRepository historialRepository;

    public void registrarSalida(String placa, Long idParqueadero) {

        Optional<Parqueadero> optionalParqueadero = parqueaderoRepository.findById(idParqueadero);

        if (!optionalParqueadero.isPresent()) {
            throw new ParqueaderoNoEncontradoException(idParqueadero);
        }

        Optional<Vehiculo> optionalVehiculo = vehiculoRepository.findByPlaca(placa);

        if (!optionalVehiculo.isPresent()) {
            throw new VehiculoNoEncontradoException(placa);
        }

        if (optionalVehiculo.get().getFechaIngreso() == null) {
            throw new VehiculoNoAsignadoException(placa);
        }

        if (optionalVehiculo.get().getParqueadero().getId() != idParqueadero) {
            throw new ParqueaderoException("El vehiculo no se encuentra en este parqueadero");
        }

        Parqueadero parqueadero = optionalParqueadero.get();

        Vehiculo vehiculo = optionalVehiculo.get();

        Historial historial = new Historial();

        historial.setFechaIngreso(vehiculo.getFechaIngreso());
        historial.setFechaSalida(LocalDateTime.now());
        historial.setDuracionSegundos(vehiculo.getFechaIngreso().until(LocalDateTime.now(),
                ChronoUnit.SECONDS));
        vehiculo.addHistorial(historial);
        historial.setParqueadero_id(parqueadero);
        // historial.addVehiculo(vehiculo);
        // historial.addParqueadero(parqueadero);

        historialRepository.save(historial);
        // historialRepository.createHistorial(historial);

        // parqueadero.removeVehiculo(vehiculo);
        vehiculo.setParqueadero(null);
        vehiculo.setFechaIngreso(null);
        vehiculo.setFechaSalida(LocalDateTime.now());
        vehiculoRepository.save(vehiculo);
        parqueaderoRepository.save(parqueadero);
        // vehiculoRepository.save(vehiculo);

    }

    // ver los vehiculos de un parqueadero

    public List<Vehiculo> obtenerVehiculos() {

        return vehiculoRepository.findAll();
    }

    // ver los vehiculos por id de un parqueadero (Los vehiculos que se encuentran
    // en ese parqueadero)

    public List<Vehiculo> obtenerVehiculosPorParqueadero(Long idParqueadero) {

        Optional<Parqueadero> optionalParqueadero = parqueaderoRepository.findById(idParqueadero);

        if (!optionalParqueadero.isPresent()) {
            throw new ParqueaderoNoEncontradoException(idParqueadero);
        }

        if (optionalParqueadero.get().getUsuario() == null) {
            throw new ParqueaderoNoAsignadoException(idParqueadero);
        }

        Parqueadero parqueadero = optionalParqueadero.get();

        return parqueadero.getVehiculos();
    }

    // obtener parqueaderos por usuario

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Parqueadero> obtenerParqueaderosPorUsuario(Long idUsuario) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);

        if (!optionalUsuario.isPresent()) {
            throw new UsuarioNoEncontradoException(idUsuario);
        }

        if (optionalUsuario.get().getParqueaderos().isEmpty()) {
            throw new ParqueaderoException("El usuario no tiene parqueaderos");
        }

        Usuario usuario = optionalUsuario.get();

        return usuario.getParqueaderos();
    }
}