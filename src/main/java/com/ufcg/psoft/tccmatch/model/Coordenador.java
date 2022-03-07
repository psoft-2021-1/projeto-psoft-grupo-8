package com.ufcg.psoft.tccmatch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coordenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cpf;

    private String nome;

    private String username;

    private String senha;

    private Coordenador() {}

    public Coordenador(Long cpf, String nome, String username, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.username = username;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String getSenha() {
        return senha;
    }

    private void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }


}
