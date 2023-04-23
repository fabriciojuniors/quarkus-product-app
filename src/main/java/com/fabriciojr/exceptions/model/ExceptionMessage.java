package com.fabriciojr.exceptions.model;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

public class ExceptionMessage {
    private Response.Status status;
    private String mensagem;
    private LocalDateTime dataHora;

    public ExceptionMessage(Response.Status status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
        this.dataHora = LocalDateTime.now();
    }

    public Response.Status getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
