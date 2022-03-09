package com.ufcg.psoft.tccmatch.DTO;

public class AlunoDTO {
	
    private String email;
    
	private Long matricula;

    private String senha;

    private String nome;
				
	private String periodoConclusao;
    
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

	public String getPeriodoConclusao() {
		return periodoConclusao;
	}

	public void setPeriodoConclusao(String periodoConclusao) {
		this.periodoConclusao = periodoConclusao;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
}
