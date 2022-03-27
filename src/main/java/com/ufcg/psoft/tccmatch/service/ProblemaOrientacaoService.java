package com.ufcg.psoft.tccmatch.service;

import java.util.List;

import com.ufcg.psoft.tccmatch.DTO.RelatorioProblemaGeralDTO;
import com.ufcg.psoft.tccmatch.model.Orientacao;
import com.ufcg.psoft.tccmatch.model.ProblemaOrientacao;
import com.ufcg.psoft.tccmatch.model.Usuario;

public interface ProblemaOrientacaoService {
	
	public ProblemaOrientacao criarProblemaOrientacao(String nomeUsuario, String problemaDescricao);
	
	public void save(ProblemaOrientacao problemaOrientacao);
	
	public boolean isProblemaAluno(String usernameAluno);

	public RelatorioProblemaGeralDTO gerarRelatorioProblemaGeralDTO(List<Orientacao> orientacoesDoPeriodo);
}
