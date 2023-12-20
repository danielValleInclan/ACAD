package com.accesodatos.hibernate.dao;

import com.accesodatos.hibernate.dominio.Sala;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class SalaDao extends AbstractDao<Sala>{

    public SalaDao(){
        setClazz(Sala.class);
    }

    @Override
    public Optional<Sala> get(String id) {
        return super.get(id);
    }

    @Override
    public List<Sala> getAll() {
        return super.getAll();
    }

    @Override
    public void save(Sala sala) {
        super.save(sala);
    }

    @Override
    public void update(Sala sala) {
        super.update(sala);
    }

    @Override
    public void delete(Sala sala) {
        super.delete(sala);
    }

    @Override
    public void setClazz(Class<Sala> clazz) {
        super.setClazz(clazz);
    }

    @Override
    public EntityManager getEntityManager() {
        return super.getEntityManager();
    }
}
