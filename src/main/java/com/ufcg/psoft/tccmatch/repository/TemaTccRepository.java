package com.ufcg.psoft.tccmatch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.model.TemaTcc;

public interface TemaTccRepository extends JpaRepository<TemaTcc, Long> {
	Optional<TemaTcc> findByTitulo(String titulo);
}
