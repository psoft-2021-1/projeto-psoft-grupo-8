package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.OrientacaoCadastradaDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Orientacao;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.TemaTcc;

public interface OrientacaoService {

	public List<Orientacao> findAllOrientacaoByProfessor(Professor professor);
	
	public Optional<Orientacao> findOrientacaoByAluno(Aluno aluno);
	
	public List<Orientacao> findAllOrientacaoBySemestre(String semestre);

	public List<Orientacao> findAllFinalizadasBySemestre(String semestre);

	public List<Orientacao> findAllEmCursoBySemestre(String semestre);

	public Optional<Orientacao> getOrientacaoById(Long id);
	
	public Orientacao cadastrarOrientacao(Aluno aluno, TemaTcc temaTcc, Professor professor, String semestre);

    public void save(Orientacao orientacao);

	public List<OrientacaoCadastradaDTO> listarOrientacoesEmCursoDTO(List<Orientacao> orientacoesEmCurso);

	public List<OrientacaoCadastradaDTO> listarOrientacoesFinalizadasDTO(List<Orientacao> orientacoesFinalizadas);
}
