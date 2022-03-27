package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.DTO.AlunoDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;

public interface AlunoService extends UsuarioService<Aluno> {
	
	public Aluno criarAluno(AlunoDTO alunoDTO);

	public Aluno atualizarAluno(AlunoDTO alunoDTO, Aluno aluno);

	public void removerAluno(Aluno aluno);

}
