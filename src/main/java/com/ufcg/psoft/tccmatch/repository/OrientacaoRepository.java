package com.ufcg.psoft.tccmatch.repository;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Orientacao;
import com.ufcg.psoft.tccmatch.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrientacaoRepository extends JpaRepository<Orientacao, Long>  {

	public Optional<Orientacao> findByAluno(Aluno aluno);
    
	public List<Orientacao> findAllByProfessor(Professor professor);

    public List<Orientacao> findAllBySemestre(String semestre);

}
