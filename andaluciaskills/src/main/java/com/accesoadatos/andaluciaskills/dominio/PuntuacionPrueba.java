package com.accesoadatos.andaluciaskills.dominio;

import javax.persistence.*;

@Entity
@Table(name = "puntuacion_prueba")
public class PuntuacionPrueba {
    @Id

    @ManyToOne

    @JoinColumn(name = "competidor_id")

    private Competidor competidor;

    @Id

    @ManyToOne

        @JoinColumn(name = "prueba_id")

    private Prueba prueba;

    private int puntuacion;

    public PuntuacionPrueba() {
    }

    public PuntuacionPrueba(Competidor competidor, Prueba prueba, int puntuacion) {
        this.competidor = competidor;
        this.prueba = prueba;
        this.puntuacion = puntuacion;
    }

    public Competidor getCompetidor() {
        return competidor;
    }

    public void setCompetidor(Competidor competidor) {
        this.competidor = competidor;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
