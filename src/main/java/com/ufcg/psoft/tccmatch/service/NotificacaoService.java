package com.ufcg.psoft.tccmatch.service;

import java.util.List;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Notificacao;
import com.ufcg.psoft.tccmatch.model.TemaTcc;

public interface NotificacaoService {
	
	public void save(Notificacao notificacao);
	
	public void notificaProfessorParaAluno(TemaTcc temaTcc, List<Aluno> alunos);

}
