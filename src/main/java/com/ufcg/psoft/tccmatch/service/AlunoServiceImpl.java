package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.DTO.AlunoDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.repository.AlunoRepository;
import com.ufcg.psoft.tccmatch.repository.BaseRepository;

@Service("ALUNO")
public class AlunoServiceImpl implements AlunoService {
	
    private BaseRepository<Aluno> alunoRepository;

    public AlunoServiceImpl(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }
	
	@Override
	public Aluno criarAluno(AlunoDTO alunoDTO) {
		Aluno aluno = new Aluno(alunoDTO.getEmail(), alunoDTO.getMatricula(), alunoDTO.getSenha(), 
				alunoDTO.getNome(), alunoDTO.getPeriodoConclusao());
		return aluno;			         
	}
	
	@Override
	public Aluno atualizarAluno(AlunoDTO alunoDTO, Aluno aluno) {
		aluno.setEmail(alunoDTO.getEmail());
		aluno.setSenha(alunoDTO.getSenha());
		aluno.setPeriodoConclusao(alunoDTO.getPeriodoConclusao());
		
		return aluno;
	}
	
	@Override
	public void removerAluno(Aluno aluno) {
		alunoRepository.delete(aluno);
	}

	@Override
	public Optional<Aluno> getById(Long id) {
		return alunoRepository.findById(id);
	}

	@Override
	public void save(Aluno aluno) {
		alunoRepository.save(aluno);
	}

	@Override
	public Optional<Aluno> findByUsername(String username) {
		return alunoRepository.findByUsername(username);
	}

	@Override
	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}

}
