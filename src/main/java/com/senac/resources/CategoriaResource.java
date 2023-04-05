package com.senac.resources;

import com.senac.model.Categoria;
import com.senac.repositories.CategoriaRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.Collection;

@Path("/categorias")
public class CategoriaResource {

    @Inject
    private CategoriaRepository repository;

    @GET
    public Collection<Categoria> get() {
        return repository.findAll();
    }

    @GET
    @Path("{id}")
    public Categoria getById(@PathParam("id") final Long id) {
        return repository.findByOrElsethrow(id);
    }

    @POST
    @Transactional
    public Categoria save(final Categoria categoria) {
        return repository.save(categoria);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Categoria update(@PathParam("id") final Long id, final Categoria categoria) {
        final Categoria categoriaPersisted = repository.findByOrElsethrow(id);

        categoriaPersisted.setNome(categoria.getNome());

        return repository.save(categoriaPersisted);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") final Long id) {
        repository.deleteById(id);
    }
}
