package com.senac.repositories;

import com.senac.model.Produto;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ProdutoRepository extends AbstractRepository<Produto, Long>{
    public ProdutoRepository() {
        super(Produto.class);
    }
}
