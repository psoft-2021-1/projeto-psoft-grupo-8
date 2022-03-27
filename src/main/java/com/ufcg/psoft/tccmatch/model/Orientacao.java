package com.ufcg.psoft.tccmatch.model;

import javax.persistence.*;

@Entity
public class Orientacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno;

    @OneToOne
    private TemaTcc temaTcc;

    @ManyToOne
    private Professor professor;
    
    @OneToOne
    private ProblemaOrientacao problemaOrientacao;

    private String semestre;

    public Orientacao() { }

    public Orientacao(Aluno aluno, TemaTcc temaTcc, Professor professor, String semestre) {
        this.aluno = aluno;
        this.temaTcc = temaTcc;
        this.professor = professor;
        this.semestre = semestre;
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public TemaTcc getTemaTcc() {
        return temaTcc;
    }

    public void setTemaTcc(TemaTcc temaTcc) {
        this.temaTcc = temaTcc;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

	public ProblemaOrientacao getProblemaOrientacao() {
		return problemaOrientacao;
	}

	public void setProblemaOrientacao(ProblemaOrientacao problemaOrientacao) {
		this.problemaOrientacao = problemaOrientacao;
	}

}
