package com.ufcg.psoft.tccmatch.repository;

import com.ufcg.psoft.tccmatch.model.Solicitacao;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>  {
	
	public List<Solicitacao> findAllByUsuarioDestinatario(Usuario usuario);

	public List<Solicitacao> findAllByTemaTcc(TemaTcc temaTcc);
}
