package com.accesoadatos.andaluciaskills.dao;

import com.accesoadatos.andaluciaskills.dominio.Competidor;
import com.accesoadatos.andaluciaskills.dominio.PuntuacionPrueba;

import javax.persistence.Query;
import java.util.List;

public class PuntuacionPruebaDao extends AbstractDao<PuntuacionPrueba> {
    public PuntuacionPruebaDao(){
        setClazz(PuntuacionPrueba.class);
    }

    public List<Object[]> getPuntuacionesPruebaCompetidor(){
        String hql = "SELECT pp.puntuacion, p.nombre, c.nombre\n" +
                "FROM PuntuacionPrueba pp\n" +
                "JOIN pp.prueba p\n" +
                "JOIN pp.competidor c\n";
        Query query = getEntityManager().createQuery(hql);
        return query.getResultList();
    }
}
