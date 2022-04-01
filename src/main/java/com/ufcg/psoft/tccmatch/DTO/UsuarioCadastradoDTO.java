package com.ufcg.psoft.tccmatch.DTO;

import com.ufcg.psoft.tccmatch.model.TipoUsuario;

public class UsuarioCadastradoDTO {
    private String nome;

    private String username;

    private String email;

    private TipoUsuario tipoUsuario;

    public UsuarioCadastradoDTO(String nome, String username, String email, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}