package com.ufcg.psoft.tccmatch.repository;

import java.util.Optional;

import com.ufcg.psoft.tccmatch.model.Coordenador;

public interface CoordenadorRepository extends BaseRepository<Coordenador> {
	
    default String getTipoUsuario() {
        return "CoordenadorRepository";
    }
	
	Optional<Coordenador> findByUsername(String username);
}
