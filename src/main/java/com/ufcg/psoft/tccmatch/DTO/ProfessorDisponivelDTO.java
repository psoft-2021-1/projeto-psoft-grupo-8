package com.ufcg.psoft.tccmatch.DTO;

import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;

import java.util.List;

public class ProfessorDisponivelDTO {

    private String email;

    private String nome;

    private List<AreaDeEstudo> areasDeEstudo;
    
    private ProfessorDisponivelDTO() {}
    
    public ProfessorDisponivelDTO(String email, String nome, List<AreaDeEstudo> areasDeEstudo) {
    	this.email = email;
    	this.nome = nome;
    	this.areasDeEstudo = areasDeEstudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<AreaDeEstudo> getAreasDeEstudo() {
        return areasDeEstudo;
    }

    public void setAreasDeEstudo(List<AreaDeEstudo> areasDeEstudo) {
        this.areasDeEstudo = areasDeEstudo;
    }

}
