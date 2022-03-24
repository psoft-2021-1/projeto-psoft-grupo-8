package com.ufcg.psoft.tccmatch.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class TemaTcc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	
	private String titulo;
	
	private String descricao;
	
	private String status;

	@ManyToMany
	private List<AreaDeEstudo> areasDeEstudosRelacionadas;
	
	private TemaTcc() {}
	
	/*
	 * Construtor para aluno
	 */
	public TemaTcc(String username, String titulo, String descricao, String status, List<AreaDeEstudo> areasDeEstudoRelacionadas) {
		this.username = username;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = status;
		this.areasDeEstudosRelacionadas = areasDeEstudoRelacionadas;
	}

	/*
	 * Construtor para professor
	 */
	public TemaTcc(String username, String titulo, String descricao, List<AreaDeEstudo> areasDeEstudoRelacionadas) {
		this.username = username;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = null;
		this.areasDeEstudosRelacionadas = areasDeEstudoRelacionadas;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AreaDeEstudo> getAreasDeEstudosRelacionadas() {
		return areasDeEstudosRelacionadas;
	}

	public void setAreasDeEstudosRelacionadas(List<AreaDeEstudo> areasDeEstudosRelacionadas) {
		this.areasDeEstudosRelacionadas = areasDeEstudosRelacionadas;
	}
	
	@Override
	public String toString() {
		return titulo;
	}
	
}
