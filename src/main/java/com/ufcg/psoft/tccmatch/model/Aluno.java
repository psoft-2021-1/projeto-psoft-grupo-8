package com.ufcg.psoft.tccmatch.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Aluno extends Usuario{
	    
    private String periodoConclusao;
    
    @ManyToMany
    private List<AreaDeEstudo> areasDeEstudo;
    
    private Aluno() {}
    
    public Aluno(String email, Long matricula, String senha, String nome, String periodoConclusao) {
        super(email, matricula.toString(), senha, nome);
        this.periodoConclusao = periodoConclusao;
        this.areasDeEstudo = new ArrayList<AreaDeEstudo>();
    }

	public String getPeriodoConclusao() {
		return periodoConclusao;
	}

	public void setPeriodoConclusao(String periodoConclusao) {
		this.periodoConclusao = periodoConclusao;
	}

	public List<AreaDeEstudo> getAreasDeEstudo() {
		return areasDeEstudo;
	}

	public void setAreasDeEstudo(List<AreaDeEstudo> areasDeEstudo) {
		this.areasDeEstudo = areasDeEstudo;
	}
}
