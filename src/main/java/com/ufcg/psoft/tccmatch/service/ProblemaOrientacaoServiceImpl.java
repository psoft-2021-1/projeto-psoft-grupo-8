package com.ufcg.psoft.tccmatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.model.ProblemaOrientacao;
import com.ufcg.psoft.tccmatch.model.Usuario;
import com.ufcg.psoft.tccmatch.repository.ProblemaOrientacaoRepository;

@Service
public class ProblemaOrientacaoServiceImpl implements ProblemaOrientacaoService{

	@Autowired
	ProblemaOrientacaoRepository problemaOrientacaoRepository;
	
	@Override
	public ProblemaOrientacao criarProblemaOrientacao(String nomeUsuario, String problemaDescricao) {
		ProblemaOrientacao problemaOrientacao = new ProblemaOrientacao(nomeUsuario, problemaDescricao);
		return problemaOrientacao;	
	}

	@Override
	public void save(ProblemaOrientacao problemaOrientacao) {
		problemaOrientacaoRepository.save(problemaOrientacao);
	}

}
