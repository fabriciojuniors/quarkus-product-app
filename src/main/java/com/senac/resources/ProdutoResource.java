package com.senac.resources;

import com.senac.model.Produto;
import com.senac.model.dto.ProdutoDto;
import com.senac.model.dto.mappers.ProdutoMapper;
import com.senac.repositories.CategoriaRepository;
import com.senac.repositories.ProdutoRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.Collection;

@Path("/produtos")
public class ProdutoResource {

    @Inject
    private ProdutoRepository repository;
    @Inject
    private CategoriaRepository categoriaRepository;
    @Inject
    private ProdutoMapper mapper;

    @GET
    public Collection<ProdutoDto> get() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @GET
    @Path("{id}")
    public ProdutoDto getById(@PathParam("id") final Long id) {
        return mapper.toDto(repository.findByOrElsethrow(id));
    }

    @POST
    @Transactional
    public ProdutoDto save(final ProdutoDto dto) {
        final Produto produto = repository.save(mapper.fromDto(dto));
        return mapper.toDto(produto);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public ProdutoDto update(@PathParam("id") final Long id, final ProdutoDto dto) {
        final Produto produtoPersisted = repository.findByOrElsethrow(id);

        produtoPersisted.setNome(dto.nome());
        produtoPersisted.setValorUnitario(dto.valorUnitario());
        produtoPersisted.setCategoria(categoriaRepository.findByOrElsethrow(dto.categoria().id()));

        return mapper.toDto(repository.save(produtoPersisted));
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") final Long id) {
        repository.deleteById(id);
    }

}