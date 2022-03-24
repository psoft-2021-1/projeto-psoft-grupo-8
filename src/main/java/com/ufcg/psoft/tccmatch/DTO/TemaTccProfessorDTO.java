package com.ufcg.psoft.tccmatch.DTO;

public class TemaTccProfessorDTO {

	private String titulo;

	private String descricao;

	private String[] areasDeEstudoRelacionadas;

	public String getTitulo() {
		return titulo.trim().toUpperCase();
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

	public String[] getAreasDeEstudoRelacionadas() {
		return areasDeEstudoRelacionadas;
	}

	public void setAreasDeEstudoRelacionadas(String[] areasDeEstudoRelacionadas) {
		this.areasDeEstudoRelacionadas = areasDeEstudoRelacionadas;
	}

}
