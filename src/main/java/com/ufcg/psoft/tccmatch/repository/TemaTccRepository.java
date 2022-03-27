package com.ufcg.psoft.tccmatch.repository;

import com.ufcg.psoft.tccmatch.model.TemaTcc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemaTccRepository extends JpaRepository<TemaTcc, Long> {
	
	Optional<TemaTcc> findByTitulo(String titulo);
	
}
