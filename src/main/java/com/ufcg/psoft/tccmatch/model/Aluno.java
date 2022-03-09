package com.ufcg.psoft.tccmatch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;
    
    private Long matricula;

    private String senha;
    
    private String email;
    
    private String periodoConclusao;

    private Aluno() {}

    public Aluno(Long matricula, String nomeCompleto, String email, String periodoConclusao) {
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.senha = matricula.toString();
        this.periodoConclusao = periodoConclusao;
    }

	public String getPeriodoConclusao() {
		return periodoConclusao;
	}

	public void setPeriodoConclusao(String periodoConclusao) {
		this.periodoConclusao = periodoConclusao;
	}

	public Long getId() {
		return id;
	}

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
