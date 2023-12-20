package com.accesodatos.hibernate.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sala")
public class Sala {
    @Id
    private String id;
    private int capacidad;
    private String descripcion;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
    private List<Reunion> reuniones = new ArrayList<>();


    public Sala(){
        super();
    }

    public Sala(String id, int capacidad, String descripcion){
        super();
        this.id = id;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
    }

    public void addReunion(Reunion reunion) {
        this.reuniones.add(reunion);
    }

    public boolean containsReunion(Reunion reunion) {
        return this.reuniones.stream().anyMatch(reunion::equals);
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id='" + id + '\'' +
                ", capacidad=" + capacidad +
                ", descripcion='" + descripcion + '\'' +
                ", reuniones=" + reuniones +
                '}';
    }
}
