package com.accesoadatos.andaluciaskills.dominio;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "competidor")
public class Competidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String centro;
    private String nombre;
    @OneToMany(mappedBy = "competidor")
    private Set<Especialidad> especialidades;

    @ManyToMany
    @JoinTable(
            name = "puntuacion_prueba",
            joinColumns = @JoinColumn(name = "competidor_id"),
            inverseJoinColumns = @JoinColumn(name = "prueba_id")
    )
    private Set<Prueba> pruebas = new HashSet<>();


    public Competidor() {
    }

    public Competidor(int id, String centro, String nombre, Usuario usuario) {
        this.id = id;
        this.centro = centro;
        this.nombre = nombre;
    }

    public Set<Prueba> getPruebas() {
        return pruebas;
    }

    public void setPruebas(Set<Prueba> pruebas) {
        this.pruebas = pruebas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

}
