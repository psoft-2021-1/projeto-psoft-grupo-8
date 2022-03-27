package com.ufcg.psoft.tccmatch.repository;

import com.ufcg.psoft.tccmatch.model.Coordenador;

import java.util.Optional;

public interface CoordenadorRepository extends BaseRepository<Coordenador> {
	
    default String getTipoUsuario() {
        return "CoordenadorRepository";
    }
	
	Optional<Coordenador> findByUsername(String username);
}
