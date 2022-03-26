package com.ufcg.psoft.tccmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.TemaInteresse;

public interface TemaInteresseRepository extends JpaRepository<TemaInteresse, Long>{
	
	public List<TemaInteresse> findAllByAluno(Aluno aluno);

}
