package com.ufcg.psoft.tccmatch.DTO;

import com.ufcg.psoft.tccmatch.model.TipoUsuario;

public class UsarioLoginDTO extends UsuarioCadastradoDTO {

    private Long tokenUsuario;

    public UsarioLoginDTO(String nome, String username, String email, TipoUsuario tipoUsuario, Long tokenUsuario) {
        super(nome, username, email, tipoUsuario);
        this.tokenUsuario = tokenUsuario;
    }

    public Long getTokenUsuario() {
        return tokenUsuario;
    }

    public void setTokenUsuario(Long tokenUsuario) {
        this.tokenUsuario = tokenUsuario;
    }
    
}
