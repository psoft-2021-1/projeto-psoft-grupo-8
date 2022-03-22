package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.TemaTccDTO;
import com.ufcg.psoft.tccmatch.model.TemaTcc;

public interface TemaTccService {
	public TemaTcc criarTemaTcc(TemaTccDTO temaTccDTO);

	public void save(TemaTcc temaTcc);

	public Optional<TemaTcc> getByTitulo(String titulo);

	public List<TemaTcc> getTemasTcc();

	public List<TemaTcc> getTemasByTitulo(String[] temasTitulos);
}
