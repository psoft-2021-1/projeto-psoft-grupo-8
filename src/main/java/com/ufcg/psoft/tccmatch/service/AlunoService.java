package com.ufcg.psoft.tccmatch.service;

import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.AlunoDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;

public interface AlunoService {
	
	public Aluno criarAluno(AlunoDTO alunoDTO);
	
	public Optional<Aluno> getAlunoByMatricula(Long matricula);
	
	public void salvarAluno(Aluno aluno);
	
	public Aluno atualizaCliente(AlunoDTO alunoDTO, Aluno aluno);

}
