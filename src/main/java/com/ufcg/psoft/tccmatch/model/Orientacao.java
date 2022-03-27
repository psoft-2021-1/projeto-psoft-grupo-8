package com.ufcg.psoft.tccmatch.model;

import javax.persistence.*;

@Entity
public class Orientacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Aluno aluno;

    @OneToOne
    private TemaTcc temaTcc;

    @ManyToOne
    private Professor professor;

    private String semestre;

    private boolean finalizada;

    public Orientacao() { }

    public Orientacao(Aluno aluno, TemaTcc temaTcc, Professor professor, String semestre) {
        this.aluno = aluno;
        this.temaTcc = temaTcc;
        this.professor = professor;
        this.semestre = semestre;
        this.finalizada = false;
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

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }
}
