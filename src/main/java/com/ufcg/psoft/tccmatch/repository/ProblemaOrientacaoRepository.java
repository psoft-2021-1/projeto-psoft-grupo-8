package com.ufcg.psoft.tccmatch.repository;

import com.ufcg.psoft.tccmatch.model.Orientacao;
import com.ufcg.psoft.tccmatch.model.ProblemaOrientacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemaOrientacaoRepository extends JpaRepository<ProblemaOrientacao, Long>{

	public List<ProblemaOrientacao> findAllByOrientacao(Orientacao orientacao);
	
}
