package com.ufcg.psoft.tccmatch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.SolicitacaoOrientacao;

public interface SolicitacaoOrientacaoRepository extends JpaRepository<SolicitacaoOrientacao, Long>  {
	
	public List<SolicitacaoOrientacao> findAllByProfessor(Professor professor);
}
