package com.ufcg.psoft.tccmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.tccmatch.model.Orientacao;
import com.ufcg.psoft.tccmatch.model.ProblemaOrientacao;

public interface ProblemaOrientacaoRepository extends JpaRepository<ProblemaOrientacao, Long>{

	public List<ProblemaOrientacao> findAllByOrientacao(Orientacao orientacao);
	
}
