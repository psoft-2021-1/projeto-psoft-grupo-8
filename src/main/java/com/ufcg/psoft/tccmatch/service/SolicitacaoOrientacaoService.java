package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.ufcg.psoft.tccmatch.model.TemaTcc;

public interface SolicitacaoOrientacaoService {
	
	public void save(SolicitacaoOrientacao solicitacaoOrientacao);
	
	public SolicitacaoOrientacao criarSolicitacao(Aluno aluno, Professor professor, TemaTcc temaTcc);
	
	public Optional<SolicitacaoOrientacao> getById(Long id);
	
	public List<SolicitacaoOrientacao> getSolicitacoesRecebidas(Professor professor);
}
