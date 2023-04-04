package com.senac.repositories;

import java.util.Collection;

public interface IRepository<T, I> {

    T findByOrElsethrow(I id);
    Collection<T> findAll();
    T save(T entity);
    void delete(T entity);
    void deleteById(I id);

}
