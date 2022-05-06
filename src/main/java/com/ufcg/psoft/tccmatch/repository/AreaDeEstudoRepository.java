package com.ufcg.psoft.tccmatch.repository;

import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaDeEstudoRepository extends JpaRepository<AreaDeEstudo, Long>  {
	Optional<AreaDeEstudo> findByNome(String nome);
}
