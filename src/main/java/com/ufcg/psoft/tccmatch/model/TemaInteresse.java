package com.ufcg.psoft.tccmatch.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TemaInteresse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private Professor professorInteressado;
	
	@ManyToOne
	private TemaTcc temaTcc;
	
	private boolean aprovado;
	
	public TemaInteresse() {}
	
	public TemaInteresse(Aluno aluno, Professor professorInteressado, TemaTcc temaTcc) {
		this.aluno = aluno;
		this.professorInteressado = professorInteressado;
		this.temaTcc = temaTcc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Professor getProfessorInteressado() {
		return professorInteressado;
	}

	public void setProfessorInteressado(Professor professorInteressado) {
		this.professorInteressado = professorInteressado;
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
}
