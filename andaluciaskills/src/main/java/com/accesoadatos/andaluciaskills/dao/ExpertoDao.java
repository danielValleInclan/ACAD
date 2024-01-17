package com.accesoadatos.andaluciaskills.dao;

import com.accesoadatos.andaluciaskills.dominio.Experto;

public class ExpertoDao extends AbstractDao<Experto> {
    public ExpertoDao(){
        setClazz(Experto.class);
    }
}
