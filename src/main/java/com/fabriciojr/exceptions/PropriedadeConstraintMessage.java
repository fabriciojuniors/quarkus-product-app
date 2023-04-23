package com.fabriciojr.exceptions;

public class PropriedadeConstraintMessage {
    private String propriedade;
    private String mensagem;

    public PropriedadeConstraintMessage(String propriedade, String mensagem) {
        this.propriedade = propriedade;
        this.mensagem = mensagem;
    }

    public String getPropriedade() {
        return propriedade;
    }

    public String getMensagem() {
        return mensagem;
    }
}
