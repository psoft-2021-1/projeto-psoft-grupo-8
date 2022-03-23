package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.TemaTccDTOAluno;
import com.ufcg.psoft.tccmatch.DTO.TemaTccDTOProfessor;
import com.ufcg.psoft.tccmatch.model.TemaTcc;

public interface TemaTccService {

	public TemaTcc criarTemaTccAluno(TemaTccDTOAluno temaTccDTO, String username);

	public TemaTcc criarTemaTccProfessor(TemaTccDTOProfessor temaTccDTO, String username);

	public void save(TemaTcc temaTcc);

	public Optional<TemaTcc> getByTitulo(String titulo);

	public List<TemaTcc> getTemasTcc();

	public List<TemaTcc> getTemasByTitulo(String[] temasTitulos);

}
