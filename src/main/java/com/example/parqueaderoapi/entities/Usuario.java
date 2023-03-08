package com.example.parqueaderoapi.entities;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

//import org.springframework.boot.context.properties.bind.Name;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "nombre")
    @NotNull
    @NotBlank(message = "El Nombre es obligatorio!")
    private String nombre;

    @Column(name = "rol")
    @NotNull
    private String rol;

    @Column(name = "correo")
    @NotNull
    @NotBlank(message = "El Correo es obligatorio!")
    @Email(message = "El Correo debe ser válido!")
    private String correo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonManagedReference
    private List<Parqueadero> parqueaderos = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String rol, String correo, List<Parqueadero> parqueaderos) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.correo = correo;
        this.parqueaderos = parqueaderos;
    }

    public Usuario(String nombre, String rol, String correo, List<Parqueadero> parqueaderos) {
        this.nombre = nombre;
        this.rol = rol;
        this.correo = correo;
        this.parqueaderos = parqueaderos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Parqueadero> getParqueaderos() {
        return parqueaderos;
    }

    public void setParqueaderos(List<Parqueadero> parqueaderos) {
        this.parqueaderos = parqueaderos;
    }

    public void addParqueadero(Parqueadero parqueadero) {
        parqueaderos.add(parqueadero);
        parqueadero.setUsuario(this);
    }

}
