package com.ufcg.psoft.tccmatch.DTO;

public class AreaDeEstudoDTO {
	
	private String nome;
	
	public AreaDeEstudoDTO(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome.trim().toUpperCase();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
