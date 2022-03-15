package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.DTO.ProfessorDTO;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.repository.ProfessorRepository;
import com.ufcg.psoft.tccmatch.repository.BaseRepository;

@Service("PROFESSOR")
public class ProfessorServiceImpl implements ProfessorService {
	private BaseRepository<Professor> professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }
	
	@Override
	public Professor criarProfessor(ProfessorDTO professorDTO) {
		Professor professor = new Professor(professorDTO.getEmail(), professorDTO.getCPF(), professorDTO.getSenha(), 
				professorDTO.getNome(), professorDTO.getLaboratorios());
		return professor;			         
	}
	
	@Override
	public Professor atualizarProfessor(ProfessorDTO professorDTO, Professor professor) {
		professor.setEmail(professorDTO.getEmail());  //nome e username (por ser o cpf) não podem ser alterados
		professor.setSenha(professorDTO.getSenha());
		professor.setLaboratorios(professorDTO.getLaboratorios());
		return professor;
	}
	
	@Override
	public void removerProfessor(Professor professor) {
		professorRepository.delete(professor);
	}

	@Override
	public Optional<Professor> getById(Long id) {
		return professorRepository.findById(id);
	}

	@Override
	public void save(Professor professor) {
		professorRepository.save(professor);
	}

	@Override
	public Optional<Professor> findByUsername(String username) {
		return professorRepository.findByUsername(username);
	}

	@Override
	public List<Professor> findAll() {
		return professorRepository.findAll();
	}
	
	
}
