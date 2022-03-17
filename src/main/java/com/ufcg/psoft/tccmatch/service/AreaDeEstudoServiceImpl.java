package com.ufcg.psoft.tccmatch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.DTO.AreaDeEstudoDTO;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.repository.AreaDeEstudoRepository;

@Service
public class AreaDeEstudoServiceImpl implements AreaDeEstudoService {
	
	@Autowired
	private AreaDeEstudoRepository areaDeEstudoRepository;
	
	@Override
	public AreaDeEstudo criarAreaDeEstudo(AreaDeEstudoDTO areaDeEstudoDTO) {
		AreaDeEstudo areaDeEstudo = new AreaDeEstudo(areaDeEstudoDTO.getNome());
		return areaDeEstudo;
	}

	@Override
	public void save(AreaDeEstudo areaDeEstudo) {
		areaDeEstudoRepository.save(areaDeEstudo);
	}
	
	@Override
	public Optional<AreaDeEstudo> getByNome(String nome) {
		return areaDeEstudoRepository.findByNome(nome);
	}
	
	@Override
	public List<AreaDeEstudo> listarAreasDeEstudo() {
		return areaDeEstudoRepository.findAll();
	}
	
	@Override
	public List<AreaDeEstudo> getAreasByNome(String[] areasNomes) {
		List<AreaDeEstudo> areasDeEstudos = new ArrayList<AreaDeEstudo>();
		
		for (String nome : areasNomes) {
			AreaDeEstudo areaDeEstudo = getByNome(nome.toUpperCase()).get();
			areasDeEstudos.add(areaDeEstudo);
		}
		
		return areasDeEstudos;
	}
}
