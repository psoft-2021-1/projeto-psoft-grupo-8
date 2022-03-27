package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.model.ProblemaOrientacao;
import com.ufcg.psoft.tccmatch.model.Usuario;

public interface ProblemaOrientacaoService {
	
	public ProblemaOrientacao criarProblemaOrientacao(String nomeUsuario, String problemaDescricao);
	
	public void save(ProblemaOrientacao problemaOrientacao);
}
