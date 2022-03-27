package com.ufcg.psoft.tccmatch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notificacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String content;
	
	private String emailRemetente;
	
	public Notificacao() {}
	
	public Notificacao(String content, String emailRemetente) {
		this.content = content;
		this.emailRemetente = emailRemetente;		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return content;
	}

	public String getEmailRemetente() {
		return emailRemetente;
	}

	public void setEmailRemetente(String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}

}
