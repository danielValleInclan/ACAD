package com.accesoadatos.andaluciaskills.dominio;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prueba")
public class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique=true)
    private String nombre;

    @ManyToMany(mappedBy = "pruebas")
    private Set<Competidor> competidores = new HashSet<>();;

    public Prueba() {
    }

    public Prueba(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Set<Competidor> getCompetidores() {
        return competidores;
    }

    public void setCompetidores(Set<Competidor> competidores) {
        this.competidores = competidores;
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
}
