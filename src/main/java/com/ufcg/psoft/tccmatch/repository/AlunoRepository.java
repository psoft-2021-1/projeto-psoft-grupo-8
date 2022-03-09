package com.ufcg.psoft.tccmatch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.tccmatch.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	Optional<Aluno> findByMatricula(Long matricula);
}

