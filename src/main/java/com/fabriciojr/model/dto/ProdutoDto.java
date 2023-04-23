package com.fabriciojr.model.dto;

import java.math.BigDecimal;

public record ProdutoDto(
        Long id,
        String nome,
        BigDecimal valorUnitario,
        CategoriaDto categoria) {
}
