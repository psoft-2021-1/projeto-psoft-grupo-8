package com.ufcg.psoft.tccmatch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;

public interface TemaTccRepository extends JpaRepository<TemaTcc, Long> {
	
	Optional<TemaTcc> findByTitulo(String titulo);
	
}
