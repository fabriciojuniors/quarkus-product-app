package com.senac.resources;

import com.senac.model.Produto;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.Collection;

@Path("/produtos")
public class ProdutoResource {

    @Inject
    private EntityManager em;

    @GET
    public Collection<Produto> get() {
        return em.createQuery("from Produto", Produto.class).getResultList();
    }

    @GET
    @Path("{id}")
    public Produto getById(@PathParam("id") final Long id) {
        if(id == null) {
            throw new RuntimeException("ID do produto é obrigatório");
        }
        return em.find(Produto.class, id);
    }

    @POST
    @Transactional
    public Produto save(final Produto produto) {
        em.persist(produto);
        return produto;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Produto update(@PathParam("id") final Long id, final Produto produto) {
        if(id == null) {
            throw new RuntimeException("ID do produto é obrigatório");
        }

        final Produto produtoPersisted = em.find(Produto.class, id);
        if (produtoPersisted == null) {
            throw new RuntimeException("Produto com ID " + id + " não identificado.");
        }

        produtoPersisted.setNome(produto.getNome());
        produtoPersisted.setValorUnitario(produto.getValorUnitario());

        em.merge(produtoPersisted);
        return produtoPersisted;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") final Long id) {
        if(id == null) {
            throw new RuntimeException("ID do produto é obrigatório");
        }

        final Produto produtoPersisted = em.find(Produto.class, id);
        if (produtoPersisted == null) {
            throw new RuntimeException("Produto com ID " + id + " não identificado.");
        }

        em.remove(produtoPersisted);
    }

}