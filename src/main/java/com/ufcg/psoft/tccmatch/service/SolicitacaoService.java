package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.DTO.SolicitacaoDTO;
import com.ufcg.psoft.tccmatch.model.Solicitacao;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface SolicitacaoService {
	
	public void save(Solicitacao solicitacao);
	
	public Solicitacao criarSolicitacao(Usuario usuarioRemetente, Usuario usuarioDestinatario, TemaTcc temaTcc);
	
	public Solicitacao atualizarSolicitacao(boolean decisao, String justificativa, Solicitacao solicitacao);
	
	public Optional<Solicitacao> getById(Long id);

	public void removerSolicitacao(Solicitacao solicitacao);

	public void deleteAllByTemaTcc(TemaTcc temaTcc);

    List<SolicitacaoDTO> getSolicitacoesRecebidasDTO(Usuario usuario);
}
