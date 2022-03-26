package com.ufcg.psoft.tccmatch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;

public interface AreaDeEstudoRepository extends JpaRepository<AreaDeEstudo, Long>  {
	Optional<AreaDeEstudo> findByNome(String nome);
}
