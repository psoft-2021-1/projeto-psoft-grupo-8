package com.ufcg.psoft.tccmatch.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Professor extends Usuario {
	
	private String[] laboratorios;

	private Integer quota;

	@ManyToMany
    private List<AreaDeEstudo> areasDeEstudo;
	
	public Professor() {};
	
	public Professor(String email, Long CPF, String senha, String nome, String laboratorios) {
		super(email, CPF.toString(), senha, nome);
		this.laboratorios = laboratorios.split(" ");
		this.areasDeEstudo = new ArrayList<AreaDeEstudo>();
		super.tipoUsuario = TipoUsuario.PROFESSOR;
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

	public Integer getQuota() {
		return quota;
	}

	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	public boolean isDisponivel() {
		return (quota > 0);
	}

	public boolean containsAreaDeEstudo(AreaDeEstudo areaDeEstudo) {
		return (areasDeEstudo.contains(areaDeEstudo));
	}
}