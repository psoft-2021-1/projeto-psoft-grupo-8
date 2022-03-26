package com.ufcg.psoft.tccmatch.service;

import java.util.List;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Orientacao;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.TemaTcc;

public interface OrientacaoService {

	public List<Orientacao> findAllOrientacaoByProfessor(Professor professor);
	
	public Orientacao cadastrarOrientacao(Aluno aluno, TemaTcc temaTcc, Professor professor, String semestre);

    public void save(Orientacao orientacao);
}
