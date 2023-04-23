package com.fabriciojr.resources;

import com.fabriciojr.model.Categoria;
import com.fabriciojr.model.dto.CategoriaDto;
import com.fabriciojr.model.dto.mappers.CategoriaMapper;
import com.fabriciojr.repositories.CategoriaRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.Collection;

@Path("/categorias")
public class CategoriaResource {

    @Inject
    private CategoriaRepository repository;
    @Inject
    private CategoriaMapper mapper;

    @GET
    public Collection<CategoriaDto> get() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @GET
    @Path("{id}")
    public CategoriaDto getById(@PathParam("id") final Long id) {
        return mapper.toDto(repository.findByOrElsethrow(id));
    }

    @POST
    @Transactional
    public CategoriaDto save(final CategoriaDto dto) {
        final Categoria categoria = repository.save(mapper.fromDto(dto));
        return mapper.toDto(categoria);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public CategoriaDto update(@PathParam("id") final Long id, final CategoriaDto dto) {
        final Categoria categoriaPersisted = repository.findByOrElsethrow(id);

        categoriaPersisted.setNome(dto.nome());

        return mapper.toDto(repository.save(categoriaPersisted));
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") final Long id) {
        repository.deleteById(id);
    }
}
