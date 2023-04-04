package com.senac.resources;

import com.senac.model.Produto;
import com.senac.repositories.ProdutoRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.Collection;

@Path("/produtos")
public class ProdutoResource {

    @Inject
    private ProdutoRepository repository;

    @GET
    public Collection<Produto> get() {
        return repository.findAll();
    }

    @GET
    @Path("{id}")
    public Produto getById(@PathParam("id") final Long id) {
        return repository.findByOrElsethrow(id);
    }

    @POST
    @Transactional
    public Produto save(final Produto produto) {
        return repository.save(produto);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Produto update(@PathParam("id") final Long id, final Produto produto) {
        final Produto produtoPersisted = repository.findByOrElsethrow(id);

        produtoPersisted.setNome(produto.getNome());
        produtoPersisted.setValorUnitario(produto.getValorUnitario());

        return repository.save(produtoPersisted);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") final Long id) {
        repository.deleteById(id);
    }

}