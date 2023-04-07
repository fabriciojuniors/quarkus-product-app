package com.senac.repositories;

import com.senac.model.Auditoria;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@SuppressWarnings("unchecked")
public abstract class AbstractRepository<T extends Auditoria, I> implements IRepository<T, I> {

    protected Class<T> type;
    @Inject
    private EntityManager em;
    @Inject
    private Validator validator;

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
        entity.setDhAlteracao(LocalDateTime.now());
        validar(entity);
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

    final void validar(final T entity) {
        final Set validations = validator.validate(entity);
        if(!validations.isEmpty()) {
            throw new ConstraintViolationException(validations);
        }
    }
}
