package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.model.Usuario;

public interface UsuarioService<T extends Usuario> {
	
	Optional<T> getById(Long id);

    void save(T entidade);

    Optional<T> findByUsername(String username);

    List<T> findAll();

}
