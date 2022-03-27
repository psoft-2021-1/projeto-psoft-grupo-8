package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.DTO.RelatorioProblemaGeralDTO;
import com.ufcg.psoft.tccmatch.model.Orientacao;
import com.ufcg.psoft.tccmatch.model.ProblemaOrientacao;
import com.ufcg.psoft.tccmatch.model.Usuario;

import java.util.List;

public interface ProblemaOrientacaoService {
	
	public ProblemaOrientacao criarProblemaOrientacao(Usuario usuario, String problemaDescricao, Orientacao orientacao);
	
	public void save(ProblemaOrientacao problemaOrientacao);
	
	public List<ProblemaOrientacao> findAllByOrientacao(Orientacao orientacao);

	public RelatorioProblemaGeralDTO gerarRelatorioProblemaGeralDTO(List<Orientacao> orientacoesDoPeriodo);
}
