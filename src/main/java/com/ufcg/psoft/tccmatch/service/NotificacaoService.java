package com.ufcg.psoft.tccmatch.service;

import java.util.List;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Notificacao;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;

public interface NotificacaoService {
	
	public void save(Notificacao notificacao);
	
	public List<String> listaNotificacoesUsuario(Usuario usuario);
	
	public void notificaAlunoNovoTemaTcc(TemaTcc temaTcc);
	
	public void notificaProfessorSolicitacaoAluno(TemaTcc temaTcc, Aluno aluno);

	public void notificaAlunoInteresseProfessorTema(TemaTcc temaTcc, String nomeProfessor, Aluno aluno);

}
