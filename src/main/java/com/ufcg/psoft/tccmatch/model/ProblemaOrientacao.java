package com.ufcg.psoft.tccmatch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ProblemaOrientacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    private String usernameCriador;

    private String descricaoProblema;

    public ProblemaOrientacao() { }

    public ProblemaOrientacao(String usernameCriador, String descricaoProblema) {
        this.usernameCriador = usernameCriador;
        this.descricaoProblema = descricaoProblema;
    }

    public String getUsernameCriador() {
        return usernameCriador;
    }

    public void setUsernameCriador(String usernameCriador) {
        this.usernameCriador = usernameCriador;
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

