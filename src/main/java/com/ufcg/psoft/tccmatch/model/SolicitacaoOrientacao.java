package com.ufcg.psoft.tccmatch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class SolicitacaoOrientacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@ManyToOne
//	private Aluno aluno;
//	
//	@ManyToOne
//	private Professor professor;
	
	private String usernameRemetente;
	
	private String usernameDestinatario;
	
	@ManyToOne
	private TemaTcc temaTcc;
	
	private boolean aprovado;
	
	public SolicitacaoOrientacao() {}
	
	public SolicitacaoOrientacao(String usernameRemetente, String usernameDestinatario, TemaTcc temaTcc) {
		this.usernameDestinatario = usernameDestinatario;
		this.usernameRemetente = usernameRemetente;
		this.temaTcc = temaTcc;
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
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public String getUsernameRemetente() {
		return usernameRemetente;
	}

	public void setUsernameRemetente(String usernameRemetente) {
		this.usernameRemetente = usernameRemetente;
	}

	public String getUsernameDestinatario() {
		return usernameDestinatario;
	}

	public void setUsernameDestinatario(String usernameDestinatario) {
		this.usernameDestinatario = usernameDestinatario;
	}
}
