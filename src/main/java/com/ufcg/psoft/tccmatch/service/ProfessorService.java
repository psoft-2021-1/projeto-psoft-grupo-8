package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.DTO.AreaDeEstudoDTO;
import com.ufcg.psoft.tccmatch.DTO.ProfessorDTO;
import com.ufcg.psoft.tccmatch.DTO.ProfessorDisponivelDTO;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.model.Professor;

import java.util.List;

public interface ProfessorService extends UsuarioService<Professor> {
	
	public Professor criarProfessor(ProfessorDTO professorDTO);

	public Professor atualizarProfessor(ProfessorDTO professorDTO, Professor professor);
	
	public void removerProfessor(Professor professor);

	public List<Professor> listarProfessoresDisponiveis(List<AreaDeEstudo> areasDeEstudo);
	
	public void configurarQuota(Professor professor, Integer quota);
	
}
