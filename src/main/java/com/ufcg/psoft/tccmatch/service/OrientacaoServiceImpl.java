package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.DTO.OrientacaoCadastradaDTO;
import com.ufcg.psoft.tccmatch.DTO.OrientacaoComAreaDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Orientacao;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.repository.OrientacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrientacaoServiceImpl implements OrientacaoService {

	@Autowired
	OrientacaoRepository orientacaoRepository;

	@Override
	public Orientacao cadastrarOrientacao(Aluno aluno, TemaTcc temaTcc, Professor professor, String semestre) {
		Orientacao orientacao = new Orientacao(aluno, temaTcc, professor, semestre);
		return orientacao;
	}

	@Override
	public void save(Orientacao orientacao) {
		orientacaoRepository.save(orientacao);
	}

	@Override
	public List<Orientacao> findAllOrientacaoByProfessor(Professor professor) {
		return orientacaoRepository.findAllByProfessor(professor);
	}

	@Override
	public Optional<Orientacao> findOrientacaoByAluno(Aluno aluno) {
		return orientacaoRepository.findByAluno(aluno);
	}

	@Override
	public List<Orientacao> findAllOrientacaoBySemestre(String semestre) {
		return orientacaoRepository.findAllBySemestre(semestre);
	}

	@Override
	public List<Orientacao> findAllFinalizadasBySemestre(String semestre) {
		return orientacaoRepository.findAllByFinalizadaAndSemestre(true, semestre);
	}

	@Override
	public List<Orientacao> findAllEmCursoBySemestre(String semestre) {
		return orientacaoRepository.findAllByFinalizadaAndSemestre(false, semestre);
	}

	@Override
	public Optional<Orientacao> getOrientacaoById(Long id) {
		return orientacaoRepository.findById(id);
	}

	@Override
	public List<OrientacaoCadastradaDTO> listarOrientacoesEmCursoDTO(List<Orientacao> orientacoesEmCurso) {
		List<OrientacaoCadastradaDTO> listaOrientacoesEmCursoDTO = new ArrayList<>();

		for (Orientacao orientacao : orientacoesEmCurso) {
			listaOrientacoesEmCursoDTO.add(new OrientacaoCadastradaDTO(orientacao.getId(),
					orientacao.getAluno().getUsername(), orientacao.getProfessor().getUsername(),
					orientacao.getTemaTcc().getTitulo(), orientacao.getSemestre(), orientacao.isFinalizada()));
		}
		return listaOrientacoesEmCursoDTO;
	}

	public List<OrientacaoCadastradaDTO> listarOrientacoesFinalizadasDTO(List<Orientacao> orientacoesFinalizadas) {
		List<OrientacaoCadastradaDTO> listaOrientacoesFinalizadasDTO = new ArrayList<>();

		for (Orientacao orientacao : orientacoesFinalizadas) {
			listaOrientacoesFinalizadasDTO.add(new OrientacaoCadastradaDTO(orientacao.getId(),
					orientacao.getAluno().getUsername(), orientacao.getProfessor().getUsername(),
					orientacao.getTemaTcc().getTitulo(), orientacao.getSemestre(), orientacao.isFinalizada()));
		}
		return listaOrientacoesFinalizadasDTO;
	}

	public List<OrientacaoComAreaDTO> gerarOrientacoesComAreaDTO(List<Orientacao> orientacoes) {
		List<OrientacaoComAreaDTO> orientacoesComAreaDTO = new ArrayList<>();
		for (Orientacao orientacao : orientacoes) {
			OrientacaoComAreaDTO orientacaoComAreaDTO = new OrientacaoComAreaDTO(
					Long.parseLong(orientacao.getAluno().getUsername()), orientacao.getTemaTcc().getTitulo(),
					Long.parseLong(orientacao.getProfessor().getUsername()), orientacao.getSemestre(),
					orientacao.getTemaTcc().getAreasDeEstudoRelacionadas());
			orientacoesComAreaDTO.add(orientacaoComAreaDTO);
		}
		return orientacoesComAreaDTO;
	}

}
