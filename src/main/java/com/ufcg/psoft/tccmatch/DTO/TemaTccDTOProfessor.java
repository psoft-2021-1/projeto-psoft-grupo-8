package com.ufcg.psoft.tccmatch.DTO;

import java.util.List;

import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.model.Usuario;

public class TemaTccDTOProfessor {

	private String titulo;

	private String descricao;

	private List<AreaDeEstudo> areaDeEstudoRelacionadas;

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


	public List<AreaDeEstudo> getAreaDeEstudoRelacionadas() {
		return areaDeEstudoRelacionadas;
	}

	public void setAreaDeEstudoRelacionadas(List<AreaDeEstudo> areaDeEstudoRelacionadas) {
		this.areaDeEstudoRelacionadas = areaDeEstudoRelacionadas;
	}

}
