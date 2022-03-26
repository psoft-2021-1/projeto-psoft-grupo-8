package com.ufcg.psoft.tccmatch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class SolicitacaoOrientacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private Professor professor;
	
	@ManyToOne
	private TemaTcc temaTcc;
	
	private boolean aprovado;
	
	public SolicitacaoOrientacao() {}
	
	public SolicitacaoOrientacao(Aluno aluno, Professor professor, TemaTcc temaTcc) {
		this.aluno = aluno;
		this.professor = professor;
		this.temaTcc = temaTcc;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TemaTcc getTemaTcc() {
		return temaTcc;
	}

	public void setTemaTcc(TemaTcc temaTcc) {
		this.temaTcc = temaTcc;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
}
