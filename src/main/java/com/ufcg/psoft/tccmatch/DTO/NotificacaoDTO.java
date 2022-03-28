package com.ufcg.psoft.tccmatch.DTO;

public class NotificacaoDTO {

    private String emailRemetente;

    private String content;

    public NotificacaoDTO(String emailRemetente, String content) {
        super();
        this.emailRemetente = emailRemetente;
        this.content = content;
    }

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public void setEmailRemetente(String emailRemetente) {
        this.emailRemetente = emailRemetente;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}