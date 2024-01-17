package com.accesoadatos.andaluciaskills.dao;

import com.accesoadatos.andaluciaskills.utiles.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class AbstractDao<T> implements Dao<T> {

    private EntityManager entityManager = EntityManagerUtil.getEntityManager();
    private Class<T> clazz;

    @Override
    public Optional<T> get(int id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public List<T> getAll() {
        String qlString = "FROM " + clazz.getName();
        Query query = entityManager.createQuery(qlString);
        return query.getResultList();
    }

    @Override
    public void save(T t) {
        executeInsideTransaction(entityManager ->entityManager.persist(t));

    }

    @Override
    public void update(T t) {
        executeInsideTransaction(entityManager ->entityManager.merge(t));

    }

    @Override
    public void delete(T t) {
        executeInsideTransaction(entityManager ->entityManager.remove(t));

    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }



    public EntityManager getEntityManager() {
        return entityManager;
    }

    // para las operaciones de modificación (save/update/delete necesitamos crear
    // una transacción y poder deshacer (rollback) en caso de error)
    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
