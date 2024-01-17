package com.accesoadatos.andaluciaskills.dominio;

import javax.persistence.*;

@Entity
@Table(name = "experto")
public class Experto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    @ManyToOne
    @JoinColumn
    private Especialidad especialidad;
    @OneToOne
    @JoinColumn
    private Usuario usuario;
    public Experto() {
    }

    public Experto(int id, String nombre, Especialidad especialidad, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
