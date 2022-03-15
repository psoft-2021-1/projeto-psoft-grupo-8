package com.ufcg.psoft.tccmatch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.tccmatch.model.Aluno;

public interface AlunoRepository extends BaseRepository<Aluno> {
	
    default String getTipoUsuario() {
        return "AlunoRepository";
    }
	
	Optional<Aluno> findByUsername(String username);
}

