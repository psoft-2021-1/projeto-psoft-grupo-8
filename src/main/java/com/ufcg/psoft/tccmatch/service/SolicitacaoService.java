package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.Solicitacao;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;

public interface SolicitacaoService {
	
	public void save(Solicitacao solicitacao);
	
	public Solicitacao criarSolicitacao(Usuario usuarioRemetente, Usuario usuarioDestinatario, TemaTcc temaTcc);
	
	public Solicitacao atualizarSolicitacao(boolean decisao, String justificativa, Solicitacao solicitacao);
	
	public Optional<Solicitacao> getById(Long id);
	
	public List<Solicitacao> getSolicitacoesRecebidas(Usuario usuarioDestinatario);
}
