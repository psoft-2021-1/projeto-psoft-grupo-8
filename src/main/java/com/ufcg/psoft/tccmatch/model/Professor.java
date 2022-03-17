package com.ufcg.psoft.tccmatch.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Professor extends Usuario {
	
	private String[] laboratorios;
	
	@OneToMany
    private List<AreaDeEstudo> areasDeEstudo;
	
	public Professor() {};
	
	public Professor(String email, Long CPF, String senha, String nome, String laboratorios) {
        super(email, CPF.toString(), senha, nome);
        this.laboratorios = laboratorios.split(" ");
        this.areasDeEstudo = new ArrayList<AreaDeEstudo>();
	}	
	
	public String[] getLaboratorios() {
		return this.laboratorios;
	}
	
	public void setLaboratorios(String laboratorios) {
		this.laboratorios = laboratorios.split(" ");
	}
	
	public List<AreaDeEstudo> getAreasDeEstudo() {
		return areasDeEstudo;
	}

	public void setAreasDeEstudo(List<AreaDeEstudo> areasDeEstudo) {
		this.areasDeEstudo = areasDeEstudo;
	}
}