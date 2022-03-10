package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.DTO.AlunoDTO;
import com.ufcg.psoft.tccmatch.DTO.ProfessorDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Professor;

public interface ProfessorService extends UsuarioService<Professor> {
	
	public Professor criarProfessor(ProfessorDTO professorDTO);

	public Professor atualizarProfessor(ProfessorDTO professorDTO, Professor professor);
	
}
