package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.TemaTccAlunoDTO;
import com.ufcg.psoft.tccmatch.DTO.TemaTccProfessorDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;

public interface TemaTccService {

	public TemaTcc criarTemaTccAluno(TemaTccAlunoDTO temaTccDTO, Usuario usuario);

	public TemaTcc criarTemaTccProfessor(TemaTccProfessorDTO temaTccDTO, Usuario usuario);

	public void save(TemaTcc temaTcc);

	public Optional<TemaTcc> getByTitulo(String titulo);

	public List<TemaTcc> getTemasTcc();

	public List<TemaTcc> getTemasByTitulo(String[] temasTitulos);

	public List<TemaTcc> getTemasTccProfessores();

    public List<TemaTcc> getTemasTccAlunos();
    
    public List<TemaTcc> getTemasTccProfessor(Long idProfessor);

    public Optional<Aluno> getAlunoByTema(TemaTcc temaTcc);
	
    public Optional<Professor> getProfessorByTema(TemaTcc temaTcc);

//	public TemaTcc manifestarInteresseTemaAluno(TemaTcc temaTcc, String username);
}
