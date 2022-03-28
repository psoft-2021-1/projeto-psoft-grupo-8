package com.ufcg.psoft.tccmatch.DTO;

public class AlunoCadastradoDTO {
    private String nome;

    private String matricula;

    private String periodoConclusao;

    private String email;

    public AlunoCadastradoDTO(String nome, String matricula, String periodoConclusao, String email) {
        super();
        this.nome = nome;
        this.matricula = matricula;
        this.periodoConclusao = periodoConclusao;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

}
