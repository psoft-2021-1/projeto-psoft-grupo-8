package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.model.Coordenador;
import com.ufcg.psoft.tccmatch.repository.CoordenadorRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordenadorServiceImpl implements  CoordenadorService {

    @Autowired
    private CoordenadorRepository coordenadorRepository;

    @Override
	public Optional<Coordenador> getCoordenadorById(Long id) {
		return coordenadorRepository.findById(id);
	}
}
