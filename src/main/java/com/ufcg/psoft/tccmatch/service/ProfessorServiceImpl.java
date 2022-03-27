package com.ufcg.psoft.tccmatch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.ProfessorDisponivelDTO;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.DTO.ProfessorDTO;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.Usuario;
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
	public List<ProfessorDisponivelDTO> listarProfessoresDisponiveis(List<AreaDeEstudo> areasDeEstudoAluno) {
		List<Professor> professores = findAll();
		List<ProfessorDisponivelDTO> professoresDisponiveis = new ArrayList<>();

		for (Professor professor : professores) {
			if (professor.isDisponivel()  && verificaAreasDeEstudo(areasDeEstudoAluno, professor)) {
				professoresDisponiveis.add(new ProfessorDisponivelDTO(professor.getEmail(), professor.getNome(), professor.getAreasDeEstudo()));
			}
		}
		return professoresDisponiveis;
	}

	private boolean verificaAreasDeEstudo(List<AreaDeEstudo> areasDeEstudoAluno, Professor professor) {
		for (AreaDeEstudo areaDeEstudoAluno : areasDeEstudoAluno) {
			if (professor.containsAreaDeEstudo(areaDeEstudoAluno)) {
				return true;
			}
		}
		return false;
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

	@Override
	public void configurarQuota(Professor professor, Integer quota) {
		professor.setQuota(quota);
	}
}
