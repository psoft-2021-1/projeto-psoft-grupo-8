package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.DTO.RelatorioProblemaGeralDTO;
import com.ufcg.psoft.tccmatch.DTO.RelatorioProblemaIndividualDTO;
import com.ufcg.psoft.tccmatch.model.Orientacao;
import com.ufcg.psoft.tccmatch.model.ProblemaOrientacao;
import com.ufcg.psoft.tccmatch.model.Usuario;
import com.ufcg.psoft.tccmatch.repository.ProblemaOrientacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemaOrientacaoServiceImpl implements ProblemaOrientacaoService{

	@Autowired
	ProblemaOrientacaoRepository problemaOrientacaoRepository;
	
	@Override
	public ProblemaOrientacao criarProblemaOrientacao(Usuario usuario, String problemaDescricao, Orientacao orientacao) {
		ProblemaOrientacao problemaOrientacao = new ProblemaOrientacao(usuario, problemaDescricao, orientacao);
		return problemaOrientacao;	
	}

	@Override
	public void save(ProblemaOrientacao problemaOrientacao) {
		problemaOrientacaoRepository.save(problemaOrientacao);
	}
	
	@Override
	public List<ProblemaOrientacao> findAllByOrientacao(Orientacao orientacao) {
		return problemaOrientacaoRepository.findAllByOrientacao(orientacao);
	}
	
	public RelatorioProblemaGeralDTO gerarRelatorioProblemaGeralDTO(List<Orientacao> orientacoesDoPeriodo) {
		List<RelatorioProblemaIndividualDTO> problemasDasOrientacoesDoPeriodoAluno = new ArrayList<RelatorioProblemaIndividualDTO>();
		List<RelatorioProblemaIndividualDTO> problemasDasOrientacoesDoPeriodoProfessor = new ArrayList<RelatorioProblemaIndividualDTO>();

		for (Orientacao orientacao: orientacoesDoPeriodo) {
			List<ProblemaOrientacao> problemas = findAllByOrientacao(orientacao);
			
			for (ProblemaOrientacao problema : problemas) {
				RelatorioProblemaIndividualDTO relatorioProblemaIndividualDTO = new RelatorioProblemaIndividualDTO(
						orientacao.getTemaTcc().getTitulo(), problema.getDescricaoProblema(), problema.getUsuarioCriador().getNome()); 
			
				if (problema.getUsuarioCriador().isAluno()) {
					problemasDasOrientacoesDoPeriodoAluno.add(relatorioProblemaIndividualDTO);
				} else {
					problemasDasOrientacoesDoPeriodoProfessor.add(relatorioProblemaIndividualDTO);
				}
			}
	
		}
		return new RelatorioProblemaGeralDTO(problemasDasOrientacoesDoPeriodoAluno, problemasDasOrientacoesDoPeriodoProfessor);
	}

}
