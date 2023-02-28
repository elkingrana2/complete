package com.example.parqueaderoapi.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parqueaderoapi.entities.Parqueadero;
import com.example.parqueaderoapi.entities.Usuario;
import com.example.parqueaderoapi.entities.Vehiculo;
import com.example.parqueaderoapi.excepcions.NombreEnUsoException;
import com.example.parqueaderoapi.excepcions.ParqueaderoNoEncontradoException;
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
    
    public void actualizarParqueadero(Long id, Parqueadero parqueaderoActualizado) {
        Optional<Parqueadero> parqueadero = parqueaderoRepository.findById(id);
        if (parqueadero.isPresent()) {
            parqueaderoActualizado.setId(id);
            parqueaderoRepository.save(parqueaderoActualizado);
        }
    }
    
    public boolean eliminarParqueadero(Long id) {
        Optional<Parqueadero> parqueaderoOptional = parqueaderoRepository.findById(id);
        if (parqueaderoOptional.isPresent()) {
            parqueaderoRepository.deleteById(id);
            return true;
        } else {
            throw new ParqueaderoNoEncontradoException(id);
        }
    }

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public void ingresarVehiculo(Vehiculo vehiculoRequest, Long parqueaderoId) {
        
        Optional<Parqueadero> optionalParqueadero = parqueaderoRepository.findById(parqueaderoId);

        Parqueadero parqueadero = optionalParqueadero.get();

        Vehiculo vehiculo;

        vehiculo= vehiculoRequest;

        vehiculo.setFechaIngreso(LocalDateTime.now());

        parqueadero.addVehiculo(vehiculo);
        parqueaderoRepository.save(parqueadero);
        vehiculoRepository.save(vehiculo);


        
    }

    
}

