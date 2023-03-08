package com.example.parqueaderoapi.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.*;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Historial")
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha_ingreso")
    @NonNull
    @NotBlank
    private LocalDateTime fechaIngreso;

    @Column(name = "fecha_salida")
    @NonNull
    @NotBlank
    private LocalDateTime fechaSalida;

    @Column(name = "duracion_segundos")
    private Long duracionSegundos;

    @ManyToOne(fetch = FetchType.LAZY)
    // @JsonBackReference
    @JoinColumn(name = "placa_vehiculo")
    @NonNull
    @NotBlank
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    // @JsonBackReference
    @JoinColumn(name = "parqueadero_id", nullable = false)
    private Parqueadero parqueadero_id;

    public Historial() {

    }

    public Historial(Long id, LocalDateTime fechaIngreso, LocalDateTime fechaSalida, Long duracionSegundos,
            Vehiculo vehiculo, Parqueadero parqueadero_id) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.duracionSegundos = duracionSegundos;
        this.vehiculo = vehiculo;
        this.parqueadero_id = parqueadero_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Long getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(Long duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo Vehiculo) {
        this.vehiculo = Vehiculo;
    }

    public Parqueadero getParqueadero_id() {
        return parqueadero_id;
    }

    public void setParqueadero_id(Parqueadero parqueadero_id) {
        this.parqueadero_id = parqueadero_id;
    }

}
