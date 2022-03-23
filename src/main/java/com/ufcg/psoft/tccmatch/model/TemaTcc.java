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

	@OneToMany
	private List<AreaDeEstudo> areaDeEstudoRelacionadas;
	
	private TemaTcc() {}
	
	public TemaTcc(String username, String titulo, String descricao, String status, List<AreaDeEstudo> areasDeEstudoRelacionadas) {
		this.username = username;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = status;
		this.areaDeEstudoRelacionadas = areasDeEstudoRelacionadas;
	}

	public TemaTcc(String username, String titulo, String descricao, List<AreaDeEstudo> areasDeEstudoRelacionadas) {
		this.username = username;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = null;
		this.areaDeEstudoRelacionadas = areasDeEstudoRelacionadas;
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

	public List<AreaDeEstudo> getAreaDeEstudoRelacionadas() {
		return areaDeEstudoRelacionadas;
	}

	public void setAreaDeEstudoRelacionadas(List<AreaDeEstudo> areaDeEstudoRelacionadas) {
		this.areaDeEstudoRelacionadas = areaDeEstudoRelacionadas;
	}
	
}
