package com.ufcg.psoft.tccmatch.DTO;

public class AlunoDTO {
	
	private Long matricula;
	
	private String nomeCompleto;
	
	private String email;
	
	private String periodoConclusao;

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPeriodoConclusao() {
		return periodoConclusao;
	}

	public void setPeriodoConclusao(String periodoConcluao) {
		this.periodoConclusao = periodoConcluao;
	}
	
}
