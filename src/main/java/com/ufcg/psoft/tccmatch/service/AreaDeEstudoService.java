package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.DTO.AreaDeEstudoDTO;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;

import java.util.List;
import java.util.Optional;

public interface AreaDeEstudoService {
	
	public AreaDeEstudo criarAreaDeEstudo(AreaDeEstudoDTO areaDeEstudoDTO);
	
	public void save(AreaDeEstudo areaDeEstudo);
	
	public Optional<AreaDeEstudo> getByNome(String nome);
		
	public List<AreaDeEstudo> listarAreasDeEstudo();
	
	public List<AreaDeEstudo> getAreasByNome(String[] areasNomes);
	
	public String verificaAreasDeEstudo(String[] areasNomes);
}
