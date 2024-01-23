package com.accesoadatos.andaluciaskills.dao;

import com.accesoadatos.andaluciaskills.dominio.Experto;

import javax.persistence.Query;
import java.util.List;

public class ExpertoDao extends AbstractDao<Experto> {
    public ExpertoDao(){
        setClazz(Experto.class);
    }

    public List<Object[]> getUsernamesDeExpertos() {
        String hql = "SELECT e.nombre, u.username\n" +
                "FROM Experto e\n" +
                "JOIN e.usuario u\n";
        Query query = getEntityManager().createQuery(hql);
        return query.getResultList();
    }

}
