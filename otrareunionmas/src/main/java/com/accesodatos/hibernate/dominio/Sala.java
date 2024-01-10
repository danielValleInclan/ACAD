package com.accesodatos.hibernate.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sala")
public class Sala {
    @Id
    @Column(length=20)
    private String id;
    private String descripcion;
    private int capacidad;
    //si no pongo cascade ALL, guardo una sala con alguna reunión distinta a la que tenía antes en BD,
    //y este cambio no se refleja en BD puesto que está en la tabla Reunion

    @OneToMany(mappedBy="sala", cascade=CascadeType.ALL)
    private List<Reunion> reuniones;

    public Sala() {
        super();
    }

    public Sala(String id, String descripcion, int capacidad) {
        super();
        this.id = id;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void addReunion(Reunion r) {
        if (this.reuniones == null) {
            reuniones = new ArrayList<Reunion>();
        }
        reuniones.add(r);
        if (!r.getSala().equals(this)) {
            r.setSala(this);
        }
    }

    public void removeReunion(Reunion r) {
        reuniones.remove(r);
        r.setSala(null);
    }

    public boolean containsReunion(Reunion r) {
        return this.reuniones.contains(r);
    }

    @Override
    public String toString() {
        return "Sala [id=" + id + ", descripcion=" + descripcion + ", capacidad=" + capacidad + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sala other = (Sala) obj;
        return Objects.equals(id, other.id);
    }
}
