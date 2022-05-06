package com.ufcg.psoft.tccmatch.repository;

import com.ufcg.psoft.tccmatch.model.Professor;

import java.util.Optional;

public interface ProfessorRepository extends BaseRepository<Professor> {
	default String getTipoUsuario() {
        return "ProfessorRepository";
    }
	
	Optional<Professor> findByUsername(String username);
}
