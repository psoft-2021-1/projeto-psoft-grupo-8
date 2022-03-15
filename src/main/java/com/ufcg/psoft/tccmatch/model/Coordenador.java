package com.ufcg.psoft.tccmatch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coordenador extends Usuario{
	
	private Long cpf;

    private Coordenador() {}

    public Coordenador(String email, String username, String senha, String nome, Long cpf) {
        super(email, username, senha, nome);
        this.setCpf(cpf);
    }

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

}
