package com.ufcg.psoft.tccmatch.DTO;

public class SolicitacaoDTO {

    private UsuarioCadastradoDTO usuarioCriador;

    private boolean decisao;

    private String justificativa;

    public SolicitacaoDTO(UsuarioCadastradoDTO usuarioCriador, boolean decisao, String justificativa) {
        super();
        this.usuarioCriador = usuarioCriador;
        this.decisao = decisao;
        this.justificativa = justificativa;
    }

    public UsuarioCadastradoDTO getUsuarioCriador() {
        return usuarioCriador;
    }

    public void setUsuarioCriador(UsuarioCadastradoDTO usuarioCriador) {
        this.usuarioCriador = usuarioCriador;
    }

    public boolean isDecisao() {
        return decisao;
    }

    public void setDecisao(boolean decisao) {
        this.decisao = decisao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

}