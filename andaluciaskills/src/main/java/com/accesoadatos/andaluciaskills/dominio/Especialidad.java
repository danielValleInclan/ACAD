package com.accesoadatos.andaluciaskills.dominio;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "especialidad")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private int codigo;
    private String nombre;

    @OneToMany(mappedBy = "especialidad")
    private Set<Experto> expertos;

    @ManyToOne
    @JoinColumn
    private Competidor competidor;

    public Especialidad() {
    }

    public Especialidad(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Set<Experto> getExpertos() {
        return expertos;
    }

    public void setExpertos(Set<Experto> expertos) {
        this.expertos = expertos;
    }

    public Competidor getCompetidor() {
        return competidor;
    }

    public void setCompetidor(Competidor competidor) {
        this.competidor = competidor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
