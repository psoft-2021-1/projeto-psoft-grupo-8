package com.ufcg.psoft.tccmatch.service;

import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.AreaDeEstudoDTO;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;

public interface AreaDeEstudoService {
	
	public AreaDeEstudo criarAreaDeEstudo(AreaDeEstudoDTO areaDeEstudoDTO);
	
	public void save(AreaDeEstudo areaDeEstudo);
	
	public Optional<AreaDeEstudo> getByNome(String nome);
}