package com.ufcg.psoft.tccmatch.repository;

import com.ufcg.psoft.tccmatch.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends Usuario> extends JpaRepository<T, Long> {

    String getTipoUsuario();

    Optional<T> findByUsername(String username);

}
