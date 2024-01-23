package com.accesoadatos.andaluciaskills.dao;

import com.accesoadatos.andaluciaskills.dominio.Competidor;

import javax.persistence.Query;
import java.util.List;

public class CompetidorDao extends AbstractDao<Competidor> {
    public CompetidorDao(){
        setClazz(Competidor.class);
    }

    public List<String> getCentros(){
        String hql = "SELECT centro FROM " + Competidor.class.getName();
        Query query = getEntityManager().createQuery(hql);
        return query.getResultList();
    }
}
