package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.AreaDeEstudoDTO;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;

public interface AreaDeEstudoService {
	
	public AreaDeEstudo criarAreaDeEstudo(AreaDeEstudoDTO areaDeEstudoDTO);
	
	public void save(AreaDeEstudo areaDeEstudo);
	
	public Optional<AreaDeEstudo> getByNome(String nome);
	
	public AreaDeEstudo getDiretamenteByNome(String nome);
	
	public List<AreaDeEstudo> listarAreasDeEstudo();
	
	public List<AreaDeEstudo> getAreasByNome(String[] areasNomes);
}
