package com.ufcg.psoft.tccmatch.service;

import java.util.List;

import com.ufcg.psoft.tccmatch.model.*;

public interface NotificacaoService {
	
	public void save(Notificacao notificacao);
	
	public List<String> listaNotificacoesUsuario(Usuario usuario);
	
	public void notificaAlunoNovoTemaTcc(TemaTcc temaTcc);
	
	public void notificaProfessorSolicitacaoAluno(TemaTcc temaTcc, Aluno aluno);

	public void notificaAlunoInteresseProfessorTema(TemaTcc temaTcc, Professor professor);

	public void notificaCoordenadorSolicitacaoAceita(SolicitacaoOrientacao solicitacao);

	public void notificaCoordenadorConfirmacaoInteresse(TemaInteresse temaInteresse);

}
