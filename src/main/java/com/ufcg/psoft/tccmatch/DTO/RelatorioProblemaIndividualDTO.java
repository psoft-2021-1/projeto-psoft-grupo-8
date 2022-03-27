package com.ufcg.psoft.tccmatch.DTO;

public class RelatorioProblemaIndividualDTO {
	
	private String tituloOrientacaoComProblema;

	private String descricaoProblema;
	
	private String nomeCriador;

	public RelatorioProblemaIndividualDTO(String tituloOrientacaoComProblema, String descricaoProblema, String nomeCriador) {
		super();
		this.tituloOrientacaoComProblema = tituloOrientacaoComProblema;
		this.descricaoProblema = descricaoProblema;
		this.nomeCriador = nomeCriador;
	}
	public String getDescricaoProblema() {
		return descricaoProblema;
	}

	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}

	public String getUsernameCriador() {
		return nomeCriador;
	}

	public void setUsernameCriador(String nomeCriador) {
		this.nomeCriador = nomeCriador;
	}

	public String getTituloOrientacaoComProblema() {
		return tituloOrientacaoComProblema;
	}

	public void setTituloOrientacaoComProblema(String tituloOrientacaoComProblema) {
		this.tituloOrientacaoComProblema = tituloOrientacaoComProblema;
	}

}
