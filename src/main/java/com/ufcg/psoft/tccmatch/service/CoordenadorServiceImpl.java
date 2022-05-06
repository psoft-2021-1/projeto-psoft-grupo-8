package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.model.Coordenador;
import com.ufcg.psoft.tccmatch.repository.BaseRepository;
import com.ufcg.psoft.tccmatch.repository.CoordenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("COORDENADOR")
public class CoordenadorServiceImpl implements CoordenadorService {

    private BaseRepository<Coordenador> coordenadorRepository;
    
    public CoordenadorServiceImpl(CoordenadorRepository coordenadorRepository) {
    	this.coordenadorRepository = coordenadorRepository;
    }

	@Override
	public Optional<Coordenador> getById(Long id) {
		return coordenadorRepository.findById(id);
	}

	@Override
	public void save(Coordenador coordenador) {
		coordenadorRepository.save(coordenador);
	}

	@Override
	public Optional<Coordenador> findByUsername(String username) {
		return coordenadorRepository.findByUsername(username);
	}

	@Override
	public List<Coordenador> findAll() {
		return coordenadorRepository.findAll();
	}
}
