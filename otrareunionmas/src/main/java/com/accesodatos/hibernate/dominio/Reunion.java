package com.accesodatos.hibernate.dominio;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reunion")
public class Reunion {

    public Reunion(int id, LocalDateTime fecha, String asunto) {
        this.id = id;
        this.fecha = fecha;
        this.asunto = asunto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // @Column(name = "fecha")
    private LocalDateTime fecha;
    // @Column(name = "asunto")
    private String asunto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
}
