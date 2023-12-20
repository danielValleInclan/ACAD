package com.accesodatos.hibernate.dao;

import com.accesodatos.hibernate.dominio.Reunion;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ReunionDao extends AbstractDao<Reunion> {
    public ReunionDao(){
        setClazz(Reunion.class);
    }

    @Override
    public Optional<Reunion> get(int id) {
        return super.get(id);
    }

    @Override
    public List<Reunion> getAll() {
        return super.getAll();
    }

    @Override
    public void save(Reunion reunion) {
        super.save(reunion);
    }

    @Override
    public void update(Reunion reunion) {
        super.update(reunion);
    }

    @Override
    public void delete(Reunion reunion) {
        super.delete(reunion);
    }

    @Override
    public void setClazz(Class<Reunion> clazz) {
        super.setClazz(clazz);
    }

    @Override
    public EntityManager getEntityManager() {
        return super.getEntityManager();
    }
}
