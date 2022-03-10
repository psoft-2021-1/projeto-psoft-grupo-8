package com.ufcg.psoft.tccmatch.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Professor extends Usuario {
	private String[] laboratorios;
	
	public Professor() {};
	
	public Professor(String email, Long CPF, String senha, String nome, String laboratorios) {
        super(email, CPF.toString(), senha, nome);
        this.laboratorios = laboratorios.split(" ");
	}	
	
	public String[] getLaboratorios() {
		return this.laboratorios;
	}
	
	public void setLaboratorios(String laboratorios) {
		this.laboratorios = laboratorios.split(" ");
	}
	
}