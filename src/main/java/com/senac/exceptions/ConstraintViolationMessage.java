package com.senac.exceptions;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Collection;

public class ConstraintViolationMessage {
    private Response.Status status;
    private Collection<PropriedadeConstraintMessage> propriedades;
    private LocalDateTime dataHora;

    public ConstraintViolationMessage(Response.Status status, Collection<PropriedadeConstraintMessage> propriedades) {
        this.status = status;
        this.propriedades = propriedades;
        this.dataHora = LocalDateTime.now();
    }

    public Response.Status getStatus() {
        return status;
    }

    public Collection<PropriedadeConstraintMessage> getPropriedades() {
        return propriedades;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
