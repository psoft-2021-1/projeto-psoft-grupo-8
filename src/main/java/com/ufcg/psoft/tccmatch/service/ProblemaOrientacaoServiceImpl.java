package com.ufcg.psoft.tccmatch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.DTO.RelatorioProblemaGeralDTO;
import com.ufcg.psoft.tccmatch.DTO.RelatorioProblemaIndividualDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Orientacao;
import com.ufcg.psoft.tccmatch.model.ProblemaOrientacao;
import com.ufcg.psoft.tccmatch.model.Usuario;
import com.ufcg.psoft.tccmatch.repository.ProblemaOrientacaoRepository;

@Service
public class ProblemaOrientacaoServiceImpl implements ProblemaOrientacaoService{

	@Autowired
	ProblemaOrientacaoRepository problemaOrientacaoRepository;
	
	@Autowired
	AlunoService alunoService;
	
	@Override
	public ProblemaOrientacao criarProblemaOrientacao(String nomeUsuario, String problemaDescricao) {
		ProblemaOrientacao problemaOrientacao = new ProblemaOrientacao(nomeUsuario, problemaDescricao);
		return problemaOrientacao;	
	}

	@Override
	public void save(ProblemaOrientacao problemaOrientacao) {
		problemaOrientacaoRepository.save(problemaOrientacao);
	}
	
	public boolean isProblemaAluno(String usernameAluno) {
		Optional<Aluno> alunoOp = alunoService.findByUsername(usernameAluno);
		return !alunoOp.isEmpty();
	}
	
	public RelatorioProblemaGeralDTO gerarRelatorioProblemaGeralDTO(List<Orientacao> orientacoesDoPeriodo) {
		List<RelatorioProblemaIndividualDTO> problemasDasOrientacoesDoPeriodoAluno = new ArrayList<RelatorioProblemaIndividualDTO>();
		List<RelatorioProblemaIndividualDTO> problemasDasOrientacoesDoPeriodoProfessor = new ArrayList<RelatorioProblemaIndividualDTO>();

		for (Orientacao orientacao: orientacoesDoPeriodo) {
			ProblemaOrientacao problemaOrientacao = orientacao.getProblemaOrientacao();
			RelatorioProblemaIndividualDTO relatorioProblemaIndividualDTO = new RelatorioProblemaIndividualDTO(orientacao.getTemaTcc().getTitulo(), problemaOrientacao.getDescricaoProblema(), problemaOrientacao.getUsernameCriador()); 
			if (problemaOrientacao != null) {
				if (this.isProblemaAluno(problemaOrientacao.getUsernameCriador())) {
					problemasDasOrientacoesDoPeriodoAluno.add(relatorioProblemaIndividualDTO);
				} else {
					problemasDasOrientacoesDoPeriodoProfessor.add(relatorioProblemaIndividualDTO);
				}
			}
		}
		return new RelatorioProblemaGeralDTO(problemasDasOrientacoesDoPeriodoAluno, problemasDasOrientacoesDoPeriodoProfessor);
	}

}
