package com.ufcg.psoft.tccmatch.DTO;

public class SolicitacaoDTO {
	
	private Long id;

    private UsuarioCadastradoDTO usuarioCriadorTema;
    
    private UsuarioCadastradoDTO usuarioRemetente;

    private boolean decisao;

    private String justificativa;

    public SolicitacaoDTO(Long id, UsuarioCadastradoDTO usuarioCriadorTema, UsuarioCadastradoDTO usuarioRemetente, boolean decisao, String justificativa) {
        super();
        this.id = id;
        this.usuarioCriadorTema = usuarioCriadorTema;
        this.usuarioRemetente = usuarioRemetente;
        this.decisao = decisao;
        this.justificativa = justificativa;
    }

    public UsuarioCadastradoDTO getUsuarioCriadorTema() {
        return usuarioCriadorTema;
    }

    public void setUsuarioCriadorTema(UsuarioCadastradoDTO usuarioCriador) {
        this.usuarioCriadorTema = usuarioCriador;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioCadastradoDTO getUsuarioRemetente() {
		return usuarioRemetente;
	}

	public void setUsuarioRemetente(UsuarioCadastradoDTO usuarioRemetente) {
		this.usuarioRemetente = usuarioRemetente;
	}
	
}