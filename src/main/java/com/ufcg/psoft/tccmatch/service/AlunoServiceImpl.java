package com.ufcg.psoft.tccmatch.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.DTO.AlunoDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.repository.AlunoRepository;

@Service
public class AlunoServiceImpl implements AlunoService {
	
	@Autowired
	AlunoRepository alunoRepository;
	
	@Override
	public Aluno criarAluno(AlunoDTO alunoDTO) {
		Aluno aluno = new Aluno(alunoDTO.getMatricula(), alunoDTO.getNomeCompleto(), 
				                alunoDTO.getEmail(), alunoDTO.getPeriodoConclusao());
		
		return aluno;			         
	}

	@Override
	public Optional<Aluno> getAlunoByMatricula(Long matricula) {
		return alunoRepository.findByMatricula(matricula);
	}

	@Override
	public void salvarAluno(Aluno aluno) {
		alunoRepository.save(aluno);
	}
	
	@Override
	public Aluno atualizaCliente(AlunoDTO alunoDTO, Aluno aluno) {	
		return null;
	}

}
