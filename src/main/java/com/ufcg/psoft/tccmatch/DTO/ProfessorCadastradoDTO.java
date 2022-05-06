package com.ufcg.psoft.tccmatch.DTO;

public class ProfessorCadastradoDTO {

    private String nome;

    private String cpf;

    private String email;

    private Integer quota;

    private String[] laboratorios;

    public ProfessorCadastradoDTO(String nome, String cpf, String email, Integer quota, String[] laboratorios) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.quota = quota;
        this.laboratorios = laboratorios;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getLaboratorios() {
        return laboratorios;
    }

    public void setLaboratorios(String[] laboratorios) {
        this.laboratorios = laboratorios;
    }

}