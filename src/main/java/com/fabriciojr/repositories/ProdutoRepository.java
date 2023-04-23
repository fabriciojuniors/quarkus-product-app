package com.fabriciojr.repositories;

import com.fabriciojr.model.Produto;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ProdutoRepository extends AbstractRepository<Produto, Long>{
}
