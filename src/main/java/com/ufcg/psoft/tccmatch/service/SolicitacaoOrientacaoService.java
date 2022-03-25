package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;

public interface SolicitacaoOrientacaoService {
	
	public void save(SolicitacaoOrientacao solicitacao);
	
	public SolicitacaoOrientacao criarSolicitacao(String usernameRemetente, String usernameDestinatario, TemaTcc temaTcc);
	
	public Optional<SolicitacaoOrientacao> getById(Long id);
	
	public List<SolicitacaoOrientacao> getSolicitacoesRecebidas(String usernameDestinatario);
}
