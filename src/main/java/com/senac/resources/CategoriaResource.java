package com.senac.resources;

import com.senac.model.Categoria;
import com.senac.repositories.CategoriaRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Collection;

@Path("/categorias")
public class CategoriaResource {

    @Inject
    private CategoriaRepository repository;

    @GET
    public Collection<Categoria> get() {
        return repository.findAll();
    }

    @POST
    @Transactional
    public Categoria save(final Categoria categoria) {
        return repository.save(categoria);
    }
}
