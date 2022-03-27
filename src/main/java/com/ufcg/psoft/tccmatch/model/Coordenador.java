package com.ufcg.psoft.tccmatch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Coordenador extends Usuario{
	
	private Long cpf;

    private Coordenador() {}

    public Coordenador(String email, String username, String senha, String nome, Long cpf) {
        super(email, username, senha, nome);
        this.cpf = cpf;
        super.tipoUsuario = TipoUsuario.COORDENADOR;
    }

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

}
