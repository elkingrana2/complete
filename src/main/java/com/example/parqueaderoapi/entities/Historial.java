package com.example.parqueaderoapi.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Historial")
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDateTime fechaIngreso;

    @Column(name = "fecha_salida", nullable = false)
    private LocalDateTime fechaSalida;

    @Column(name = "duracion_segundos", nullable = false)
    private Long duracionSegundos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;
    
    public Historial()
    {

    }

    public Historial(Long id, LocalDateTime fechaIngreso, LocalDateTime fechaSalida, Long duracionSegundos,
            Vehiculo vehiculo) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.duracionSegundos = duracionSegundos;
        this.vehiculo = vehiculo;
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

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    
}
