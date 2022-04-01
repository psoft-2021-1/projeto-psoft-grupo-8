package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService<T extends Usuario> {
	
	Optional<T> getById(Long id);

    void save(T entidade);

    Optional<T> findByUsername(String username);

    List<T> findAll();

}
