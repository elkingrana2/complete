package com.example.parqueaderoapi.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Parqueadero")
public class Parqueadero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    @NotNull
    @NotBlank
    private String nombre;

    @Column(name = "direccion")
    @NotNull
    @NotBlank
    private String direccion;

    @Column(name = "capacidad")
    @NotNull
    private int capacidad;

    @Column(name = "espacio_disponible")
    private int espacioDisponible = capacidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(orphanRemoval = true, mappedBy = "parqueadero", cascade = CascadeType.ALL)
    // @JsonManagedReference
    private List<Vehiculo> vehiculos = new ArrayList<>();

    public Parqueadero() {

    }

    public Parqueadero(Long id, String nombre, String direccion, int capacidad, Usuario usuario,
            List<Vehiculo> vehiculos) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.usuario = usuario;
        this.vehiculos = vehiculos;

    }

    public Parqueadero(String nombre, String direccion, int capacidad, Usuario usuario,
            List<Vehiculo> vehiculos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.usuario = usuario;
        this.vehiculos = vehiculos;

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public int getEspacioDisponible() {
        return espacioDisponible;
    }

    public void setEspacioDisponible(int espacioDisponible) {
        this.espacioDisponible = espacioDisponible;
    }

    public void addVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
        vehiculo.setParqueadero(this);
    }

}