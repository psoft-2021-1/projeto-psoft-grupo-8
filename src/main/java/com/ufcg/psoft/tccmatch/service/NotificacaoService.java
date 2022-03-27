package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.model.*;

import java.util.List;

public interface NotificacaoService {
	
	public void save(Notificacao notificacao);
	
	public List<String> listaNotificacoesUsuario(Usuario usuario);
	
	public void notificaAlunoNovoTemaTcc(TemaTcc temaTcc);
	
	public void notificaProfessorSolicitacaoAluno(TemaTcc temaTcc, Aluno aluno);

	public void notificaAlunoInteresseProfessorTema(TemaTcc temaTcc, Professor professor);

	public void notificaCoordenadorSolicitacaoAceita(Solicitacao solicitacao);

}
