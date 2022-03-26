package com.ufcg.psoft.tccmatch.repository;

import com.ufcg.psoft.tccmatch.model.Orientacao;
import com.ufcg.psoft.tccmatch.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrientacaoRepository extends JpaRepository<Orientacao, Long>  {

    public List<Orientacao> findAllByProfessor(Professor professor);

    public List<Orientacao> findAllBySemestre(String semestre);

}
