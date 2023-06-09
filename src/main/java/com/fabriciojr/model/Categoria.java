package com.fabriciojr.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "categorias")
public class Categoria extends Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 120, nullable = false)
    @NotNull(message = "não pode ser nulo")
    private String nome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
    private Collection<Produto> produtos;

    public Categoria() {
    }

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Collection<Produto> getProdutos() {
        return produtos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
