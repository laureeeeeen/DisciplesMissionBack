package com.celula.Cadastro.dto;

public class ContatoLiderDTO {
    private String nomeInteressado;
    private String emailInteressado;
    private String mensagem;

    public String getNomeInteressado() {
        return nomeInteressado;
    }
    public void setNomeInteressado(String nomeInteressado) {
        this.nomeInteressado = nomeInteressado;
    }

    public String getEmailInteressado() {
        return emailInteressado;
    }
    public void setEmailInteressado(String emailInteressado) {
        this.emailInteressado = emailInteressado;
    }

    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
