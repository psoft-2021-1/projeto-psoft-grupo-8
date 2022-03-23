package com.ufcg.psoft.tccmatch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.TemaTccDTOAluno;
import com.ufcg.psoft.tccmatch.DTO.TemaTccDTOProfessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.repository.TemaTccRepository;

@Service
public class TemaTccServiceImpl implements TemaTccService {

	@Autowired
	private TemaTccRepository temaTccRepository;

	@Autowired
	private AreaDeEstudoService areaDeEstudoService;

	// TODO Revisar forma que estamos criando os temas.
	@Override
	public TemaTcc criarTemaTccAluno(TemaTccDTOAluno temaTccDTO, String username) {
		return new TemaTcc(username, temaTccDTO.getTitulo(), temaTccDTO.getDescricao(), temaTccDTO.getStatus(),
				areaDeEstudoService.getAreasByNome(temaTccDTO.getAreasDeEstudoRelacionadas()));
	}

	@Override
	public TemaTcc criarTemaTccProfessor(TemaTccDTOProfessor temaTccDTO, String username) {
		return new TemaTcc(username, temaTccDTO.getTitulo(), temaTccDTO.getDescricao(),
				areaDeEstudoService.getAreasByNome(temaTccDTO.getAreasDeEstudoRelacionadas()));
	}

	@Override
	public void save(TemaTcc temaTcc) {
		temaTccRepository.save(temaTcc);
	}

	@Override
	public Optional<TemaTcc> getByTitulo(String titulo) {
		return temaTccRepository.findByTitulo(titulo);
	}

	@Override
	public List<TemaTcc> getTemasTcc() {
		return temaTccRepository.findAll();
	}

	@Override
	public List<TemaTcc> getTemasByTitulo(String[] temasTitulos) {
		List<TemaTcc> temasTcc = new ArrayList<TemaTcc>();

		for (String titulo : temasTitulos) {
			TemaTcc temaTcc = getByTitulo(titulo.toUpperCase()).get();
			temasTcc.add(temaTcc);
		}

		return temasTcc;
	}

}
