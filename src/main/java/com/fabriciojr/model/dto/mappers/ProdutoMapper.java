package com.fabriciojr.model.dto.mappers;

import com.fabriciojr.model.Produto;
import com.fabriciojr.model.dto.ProdutoDto;
import com.fabriciojr.repositories.CategoriaRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ProdutoMapper {

    @Inject
    private CategoriaRepository categoriaRepository;
    @Inject
    private CategoriaMapper categoriaMapper;

    public ProdutoDto toDto(final Produto produto) {
        return new ProdutoDto(produto.getId(), produto.getNome(), produto.getValorUnitario(), categoriaMapper.toDto(produto.getCategoria()));
    }

    public Produto fromDto(final ProdutoDto dto) {
        return new Produto(dto.id(), dto.nome(), dto.valorUnitario(), categoriaRepository.findByOrElsethrow(dto.categoria().id()));
    }

}
