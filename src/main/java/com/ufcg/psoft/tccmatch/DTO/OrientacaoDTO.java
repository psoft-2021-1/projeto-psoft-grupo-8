package com.ufcg.psoft.tccmatch.DTO;

public class OrientacaoDTO {

    private Long matriculaAluno;

    private String tituloTema;

    private Long cpfProfessor;

    private String semestre;

    public OrientacaoDTO(Long matriculaAluno, String tituloTema, Long cpfProfessor, String semestre) {
        this.matriculaAluno = matriculaAluno;
        this.tituloTema = tituloTema;
        this.cpfProfessor = cpfProfessor;
        this.semestre = semestre;
    }

    public Long getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(Long matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public String getTituloTema() {
        return tituloTema;
    }

    public void setTituloTema(String tituloTema) {
        this.tituloTema = tituloTema;
    }

    public Long getCpfProfessor() {
        return cpfProfessor;
    }

    public void setCpfProfessor(Long cpfProfessor) {
        this.cpfProfessor = cpfProfessor;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
}
