package com.ufcg.psoft.tccmatch.DTO;

public class ProfessorDTO {

	private String email;
    
    private String senha;

    private String nome;
    
	private Long cpf;
				
	private String laboratorios;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLaboratorios() {
		return laboratorios;
	}

	public void setLaboratorios(String laboratorios) {
		this.laboratorios = laboratorios;
	}

	public Long getCPF() {
		return cpf;
	}

	public void setCPF(Long CPF) {
		cpf = CPF;
	}
}
