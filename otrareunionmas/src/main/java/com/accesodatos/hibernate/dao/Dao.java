package com.accesodatos.hibernate.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(int id);
    List<T> getAll();
    void save(T t);
    void delete(T t);
    void update(T t);
}
