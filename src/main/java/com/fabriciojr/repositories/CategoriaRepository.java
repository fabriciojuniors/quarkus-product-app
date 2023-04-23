package com.fabriciojr.repositories;

import com.fabriciojr.model.Categoria;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CategoriaRepository extends AbstractRepository<Categoria, Long> {
}
