package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.model.Solicitacao;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;
import com.ufcg.psoft.tccmatch.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {
	
	@Autowired
	SolicitacaoRepository solicitacaoRepository;
	
	@Override
	public void save(Solicitacao solicitacao) {
		solicitacaoRepository.save(solicitacao);
	}
	
	@Override
	public Solicitacao criarSolicitacao(Usuario usuarioRemetente, Usuario usuarioDestinatario, TemaTcc temaTcc) {
		Solicitacao solicitacao = new Solicitacao(usuarioRemetente, usuarioDestinatario, temaTcc);
		return solicitacao;
	}
	
	@Override
	public Solicitacao atualizarSolicitacao(boolean decisao, String justificativa, Solicitacao solicitacao) {
		solicitacao.setAprovado(decisao);
    	solicitacao.setJustificativa(justificativa);
    	return solicitacao;
	}
	
	@Override
	public Optional<Solicitacao> getById(Long id) {
		return solicitacaoRepository.findById(id);
	}
	
	@Override
	public List<Solicitacao> getSolicitacoesRecebidas(Usuario usuarioDestinatario) {
		return solicitacaoRepository.findAllByUsuarioDestinatario(usuarioDestinatario);
	}

	@Override
	public void removerSolicitacao(Solicitacao solicitacao) {
		solicitacaoRepository.delete(solicitacao);
	}

	@Override
	public void deleteAllByTemaTcc(TemaTcc temaTcc) {
		List<Solicitacao> solicitacoes = solicitacaoRepository.findAllByTemaTcc(temaTcc);
		for (Solicitacao solicitacao: solicitacoes) {
			this.removerSolicitacao(solicitacao);
		}
	}
	
}
