package com.ufcg.psoft.tccmatch.repository;

import com.ufcg.psoft.tccmatch.model.Aluno;

import java.util.Optional;

public interface AlunoRepository extends BaseRepository<Aluno> {
	
    default String getTipoUsuario() {
        return "AlunoRepository";
    }
	
	Optional<Aluno> findByUsername(String username);

}

