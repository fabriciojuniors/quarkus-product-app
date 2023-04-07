package com.senac.model.dto.mappers;

import com.senac.model.Categoria;
import com.senac.model.dto.CategoriaDto;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CategoriaMapper {

    public CategoriaDto toDto(final Categoria categoria) {
        return new CategoriaDto(categoria.getId(), categoria.getNome());
    }

    public Categoria fromDto(final CategoriaDto dto) {
        return new Categoria(dto.id(), dto.nome());
    }

}
