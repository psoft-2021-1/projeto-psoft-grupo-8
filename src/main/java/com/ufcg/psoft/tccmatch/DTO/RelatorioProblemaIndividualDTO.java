package com.ufcg.psoft.tccmatch.DTO;

public class RelatorioProblemaIndividualDTO {
	
	private String tituloOrientacaoComProblema;

	private String descricaoProblema;
	
	private String usernameCriador;

	public RelatorioProblemaIndividualDTO(String tituloOrientacaoComProblema, String descricaoProblema, String usernameCriador) {
		super();
		this.tituloOrientacaoComProblema = tituloOrientacaoComProblema;
		this.descricaoProblema = descricaoProblema;
		this.usernameCriador = usernameCriador;
	}
	public String getDescricaoProblema() {
		return descricaoProblema;
	}

	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}

	public String getUsernameCriador() {
		return usernameCriador;
	}

	public void setUsernameCriador(String usernameCriador) {
		this.usernameCriador = usernameCriador;
	}

	public String getTituloOrientacaoComProblema() {
		return tituloOrientacaoComProblema;
	}

	public void setTituloOrientacaoComProblema(String tituloOrientacaoComProblema) {
		this.tituloOrientacaoComProblema = tituloOrientacaoComProblema;
	}

}
