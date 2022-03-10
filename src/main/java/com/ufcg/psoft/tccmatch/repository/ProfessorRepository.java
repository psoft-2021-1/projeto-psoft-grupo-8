package com.ufcg.psoft.tccmatch.repository;

import java.util.Optional;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Professor;

public interface ProfessorRepository extends BaseRepository<Professor> {
	default String getTipoUsuario() {
        return "ProfessorRepository";
    }
	
	Optional<Professor> findByUsername(String username);
}
