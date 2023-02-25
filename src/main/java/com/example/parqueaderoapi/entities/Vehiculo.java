package com.example.parqueaderoapi.entities;
import java.util.*;
import java.time.LocalDateTime;

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

@Entity
@Table(name = "Vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "placa")
    private String placa;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "color")
    private String color;

    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;

    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parqueadero_id")
    private Parqueadero parqueadero;

    @OneToMany(orphanRemoval = true, mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<Historial> historial = new ArrayList<>();

    public Vehiculo()
    {

    }

    public Vehiculo(Long id, String placa, String modelo, String color, LocalDateTime fechaIngreso,
            LocalDateTime fechaSalida, Parqueadero parqueadero, List<Historial> historial) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.color = color;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.parqueadero = parqueadero;
        this.historial = historial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    public List<Historial> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Historial> historial) {
        this.historial = historial;
    }

    

    
}
