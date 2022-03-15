package com.ufcg.psoft.tccmatch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aluno extends Usuario{
	    
    private String periodoConclusao;

    private Aluno() {}

    public Aluno(String email, Long matricula, String senha, String nome, String periodoConclusao) {
        super(email, matricula.toString(), senha, nome);
        this.periodoConclusao = periodoConclusao;
    }

	public String getPeriodoConclusao() {
		return periodoConclusao;
	}

	public void setPeriodoConclusao(String periodoConclusao) {
		this.periodoConclusao = periodoConclusao;
	}
}
