package com.ufcg.psoft.tccmatch.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class TemaTcc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Usuario usuarioCriador;
	
	private String titulo;
	
	private String descricao;
	
	private String status;

	@ManyToMany
	private List<AreaDeEstudo> areasDeEstudoRelacionadas;
	
	private TemaTcc() {}

//	Construtor para tema do aluno
	public TemaTcc(Usuario usuarioCriador, String titulo, String descricao, String status, List<AreaDeEstudo> areasDeEstudoRelacionadas) {
		this.usuarioCriador = usuarioCriador;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = status;
		this.areasDeEstudoRelacionadas = areasDeEstudoRelacionadas;
	}

//	Construtor para tema do professor
	public TemaTcc(Usuario usuarioCriador, String titulo, String descricao, List<AreaDeEstudo> areasDeEstudoRelacionadas) {
		this.usuarioCriador = usuarioCriador;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = null;
		this.areasDeEstudoRelacionadas = areasDeEstudoRelacionadas;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<AreaDeEstudo> getAreasDeEstudoRelacionadas() {
		return areasDeEstudoRelacionadas;
	}

	public void setAreasDeEstudoRelacionadas(List<AreaDeEstudo> areasDeEstudoRelacionadas) {
		this.areasDeEstudoRelacionadas = areasDeEstudoRelacionadas;
	}
	
	@Override
	public String toString() {
		return titulo;
	}

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}
	
	public Long getUsuarioCriadorId() {
		return usuarioCriador.getId();
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}
}
