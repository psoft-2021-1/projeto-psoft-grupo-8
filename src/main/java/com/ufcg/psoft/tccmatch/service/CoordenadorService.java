package com.ufcg.psoft.tccmatch.service;

import java.util.Optional;

import com.ufcg.psoft.tccmatch.model.Coordenador;

public interface CoordenadorService {

    public Optional<Coordenador> getCoordenadorById(Long id);

}
