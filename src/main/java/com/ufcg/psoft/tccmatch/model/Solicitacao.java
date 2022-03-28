package com.ufcg.psoft.tccmatch.model;

import javax.persistence.*;

@Entity
public class Solicitacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario usuarioRemetente;

	@ManyToOne
	private Usuario usuarioDestinatario;
	
	@ManyToOne
	private TemaTcc temaTcc;
	
	private boolean decisao;
	
	private String justificativa;

	public Solicitacao() {}
	
	public Solicitacao(Usuario usuarioRemetente, Usuario usuarioDestinatario, TemaTcc temaTcc) {
		this.usuarioRemetente = usuarioRemetente;
		this.usuarioDestinatario = usuarioDestinatario;
		this.temaTcc = temaTcc;
		this.decisao = false;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TemaTcc getTemaTcc() {
		return temaTcc;
	}

	public void setTemaTcc(TemaTcc temaTcc) {
		this.temaTcc = temaTcc;
	}

	public boolean isAprovado() {
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
	
	public Usuario getUsuarioRemetente() {
		return usuarioRemetente;
	}

	public void setUsuarioRemetente(Usuario usuarioRemetente) {
		this.usuarioRemetente = usuarioRemetente;
	}

	public Usuario getUsuarioDestinatario() {
		return usuarioDestinatario;
	}

	public void setUsuarioDestinatario(Usuario usuarioDestinatario) {
		this.usuarioDestinatario = usuarioDestinatario;
	}
}
