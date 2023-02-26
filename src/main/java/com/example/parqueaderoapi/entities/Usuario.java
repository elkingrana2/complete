package com.example.parqueaderoapi.entities;

//import org.springframework.boot.context.properties.bind.Name;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "rol")
    private String rol;

    @Column(name="correo", unique = true)
    private String correo;
    
    public Usuario()
    {

    }

    public Usuario(Long id, String nombre, String rol, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.correo = correo;
    }

    public Usuario(String nombre, String rol, String correo) {
       
        this.nombre = nombre;
        this.rol = rol;
        this.correo = correo;
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
    
}
