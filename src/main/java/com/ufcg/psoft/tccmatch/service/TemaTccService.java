package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.TemaTccAlunoDTO;
import com.ufcg.psoft.tccmatch.DTO.TemaTccProfessorDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.TemaTcc;

public interface TemaTccService {

	public TemaTcc criarTemaTccAluno(TemaTccAlunoDTO temaTccDTO, String username);

	public TemaTcc criarTemaTccProfessor(TemaTccProfessorDTO temaTccDTO, String username);

	public void save(TemaTcc temaTcc);

	public Optional<TemaTcc> getByTitulo(String titulo);

	public List<TemaTcc> getTemasTcc();

	public List<TemaTcc> getTemasByTitulo(String[] temasTitulos);

	public List<TemaTcc> getTemasTccProfessores();

    public List<TemaTcc> getTemasTccAlunos();
    
    public List<TemaTcc> getTemasTccProfessor(String username);

    public Optional<Aluno> getAlunoTemaTcc(TemaTcc temaTcc);
	
	public Optional<Professor> getProfessorTemaTcc(TemaTcc temaTcc);

	public TemaTcc manifestarInteresseTemaAluno(TemaTcc temaTcc, String username);
}
