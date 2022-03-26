package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;
import com.ufcg.psoft.tccmatch.repository.SolicitacaoOrientacaoRepository;

@Service
public class SolicitacaoOrientacaoServiceImpl implements SolicitacaoOrientacaoService {
	
	@Autowired
	SolicitacaoOrientacaoRepository solicitacaoRepository;
	
	@Override
	public void save(SolicitacaoOrientacao solicitacao) {
		solicitacaoRepository.save(solicitacao);
	}
	
	@Override
	public SolicitacaoOrientacao criarSolicitacao(String usernameRemetente, String usernameDestinatario, TemaTcc temaTcc) {
		SolicitacaoOrientacao solicitacao = new SolicitacaoOrientacao(usernameRemetente, usernameDestinatario, temaTcc);
		return solicitacao;
	}
	
	@Override
	public Optional<SolicitacaoOrientacao> getById(Long id) {
		return solicitacaoRepository.findById(id);
	}
	
	@Override
	public List<SolicitacaoOrientacao> getSolicitacoesRecebidas(String usernameDestinatario) {
		return solicitacaoRepository.findAllByUsernameDestinatario(usernameDestinatario);
	}
}
