package com.ufcg.psoft.tccmatch.service;

import java.util.List;

import com.ufcg.psoft.tccmatch.DTO.AlunoDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;

public interface AlunoService extends UsuarioService<Aluno> {
	
	public Aluno criarAluno(AlunoDTO alunoDTO);

	public Aluno atualizarAluno(AlunoDTO alunoDTO, Aluno aluno);

	public void removerAluno(Aluno aluno);
	
	public List<Aluno> findByAreasDeEstudo(List<AreaDeEstudo> areasDeEstudo);

}
