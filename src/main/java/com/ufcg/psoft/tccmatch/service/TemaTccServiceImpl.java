package com.ufcg.psoft.tccmatch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.DTO.TemaTccDTO;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.repository.TemaTccRepository;

@Service
public class TemaTccServiceImpl implements TemaTccService {

	@Autowired
	private TemaTccRepository temaTccRepository;

	@Override
	public TemaTcc criarTemaTcc(TemaTccDTO temaTccDTO) {
		TemaTcc temaTcc = new TemaTcc(temaTccDTO.getTitulo(), temaTccDTO.getDescricao(), temaTccDTO.getStatus(),
				temaTccDTO.getAreaDeEstudoRelacionadas());
		return temaTcc;
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
