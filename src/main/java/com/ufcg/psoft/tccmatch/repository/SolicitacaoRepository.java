package com.ufcg.psoft.tccmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.Solicitacao;
import com.ufcg.psoft.tccmatch.model.Usuario;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>  {
	
	public List<Solicitacao> findAllByUsuarioDestinatario(Usuario usuario);
}
