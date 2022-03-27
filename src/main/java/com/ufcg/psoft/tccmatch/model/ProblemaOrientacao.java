package com.ufcg.psoft.tccmatch.model;

import javax.persistence.*;

@Entity
public class ProblemaOrientacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	@ManyToOne
    private Usuario usuario;

    private String descricaoProblema;
    
    @ManyToOne
    private Orientacao orientacao;

	public ProblemaOrientacao() { }

    public ProblemaOrientacao(Usuario usuario, String descricaoProblema, Orientacao orientacao) {
        this.usuario = usuario;
        this.descricaoProblema = descricaoProblema;
        this.orientacao = orientacao;
    }
    
    public Orientacao getOrientacao() {
    	return orientacao;
    }
    
    public void setOrientacao(Orientacao orientacao) {
    	this.orientacao = orientacao;
    }

    public Usuario getUsuarioCriador() {
        return usuario;
    }

    public void setUsuarioCriador(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

