package com.senac.repositories;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

@SuppressWarnings("unchecked")
public abstract class AbstractRepository<T, I> implements IRepository<T, I> {

    protected Class<T> type;
    @Inject
    private EntityManager em;

    public AbstractRepository() {
    }

    @PostConstruct
    public void init() {
        final ParameterizedType generic = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.type = (Class)generic.getActualTypeArguments()[0];
    }

    @Override
    public T findByOrElsethrow(I id) {
        if(id == null) {
            throw new RuntimeException("ID não pode ser nulo");
        }

        final T persisted = (T) em.find(type, id);

        if(persisted == null) {
            throw new RuntimeException(String.format("%s com ID %s não encontrado.", type.getSimpleName(), id));
        }

        return persisted;
    }

    @Override
    public Collection<T> findAll() {
        return (Collection<T>) em.createQuery("from " + type.getSimpleName(), type).getResultList();
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
