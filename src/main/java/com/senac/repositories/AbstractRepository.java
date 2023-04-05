package com.senac.repositories;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Collection;

@SuppressWarnings("unchecked")
public abstract class AbstractRepository<T, I> implements IRepository<T, I> {

    private final Class<T> T;
    @Inject
    private EntityManager em;

    public AbstractRepository(Class<T> t) {
        T = t;
    }

    @Override
    public T findByOrElsethrow(I id) {
        if(id == null) {
            throw new RuntimeException("ID não pode ser nulo");
        }

        final T persisted = (T) em.find(T, id);

        if(persisted == null) {
            throw new RuntimeException(String.format("%s com ID %s não encontrado.", T.getSimpleName(), id));
        }

        return persisted;
    }

    @Override
    public Collection<T> findAll() {
        return (Collection<T>) em.createQuery("from " + T.getSimpleName(), T).getResultList();
    }

    @Override
    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @Override
    public void deleteById(I id) {
        em.remove(findByOrElsethrow(id));
    }
}