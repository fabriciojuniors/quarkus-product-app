package com.fabriciojr.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Auditoria {

    @Column
    private LocalDateTime dhCriacao = LocalDateTime.now();
    @Column
    private LocalDateTime dhAlteracao;

    public Auditoria(LocalDateTime dhCriacao, LocalDateTime dhAlteracao) {
        this.dhCriacao = dhCriacao;
        this.dhAlteracao = dhAlteracao;
    }

    public Auditoria() {
    }

    public LocalDateTime getDhCriacao() {
        return dhCriacao;
    }

    public void setDhCriacao(LocalDateTime dhCriacao) {
        this.dhCriacao = dhCriacao;
    }

    public LocalDateTime getDhAlteracao() {
        return dhAlteracao;
    }

    public void setDhAlteracao(LocalDateTime dhAlteracao) {
        this.dhAlteracao = dhAlteracao;
    }
}
