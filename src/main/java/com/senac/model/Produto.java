package com.senac.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto extends Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 120)
    @NotNull(message = "não pode ser nulo")
    private String nome;

    @Column(nullable = false)
    @DecimalMin(value = "0.01", message = "deve ser maior que 0.01")
    @NotNull(message = "não pode ser nulo")
    private BigDecimal valorUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    @NotNull(message = "não pode ser nulo")
    private Categoria categoria;

    public Produto() {
    }

    public Produto(Long id, String nome, BigDecimal valorUnitario, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
