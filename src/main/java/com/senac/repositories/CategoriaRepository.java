package com.senac.repositories;

import com.senac.model.Categoria;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CategoriaRepository extends AbstractRepository<Categoria, Long> {
}
