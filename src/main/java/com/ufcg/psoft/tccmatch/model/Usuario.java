package com.ufcg.psoft.tccmatch.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    
    private String username;

    private String senha;

    private String nome;
    
    @Enumerated(EnumType.STRING)
    protected TipoUsuario tipoUsuario;

	@ManyToMany
    private List<Notificacao> notificacoes;

    public Usuario() {
    }

    public Usuario(String email, String username, String senha, String nome) {
        this.email = email;
        this.setUsername(username);
        this.senha = senha;
        this.nome = nome;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public List<Notificacao> getNotificacoes() {
		return notificacoes;
	}

	public void setNotificacoes(List<Notificacao> notificacoes) {
		this.notificacoes = notificacoes;
	}
	
	public void addNotificacao(Notificacao notificacao) {
		this.notificacoes.add(notificacao);
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public boolean isProfessor() {
		return this.tipoUsuario.equals(TipoUsuario.PROFESSOR);
	}
	
	public boolean isAluno() {
		return this.tipoUsuario.equals(TipoUsuario.ALUNO);
	}
}