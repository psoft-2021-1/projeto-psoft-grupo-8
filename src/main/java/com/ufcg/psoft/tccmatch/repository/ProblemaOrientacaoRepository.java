package com.ufcg.psoft.tccmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.tccmatch.model.Notificacao;
import com.ufcg.psoft.tccmatch.model.ProblemaOrientacao;

public interface ProblemaOrientacaoRepository extends JpaRepository<ProblemaOrientacao, Long>{
	
	
}
