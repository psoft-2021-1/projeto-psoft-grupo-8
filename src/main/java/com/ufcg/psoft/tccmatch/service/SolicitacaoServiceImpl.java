package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.DTO.SolicitacaoDTO;
import com.ufcg.psoft.tccmatch.DTO.UsuarioCadastradoDTO;
import com.ufcg.psoft.tccmatch.model.Solicitacao;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;
import com.ufcg.psoft.tccmatch.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {
	
	@Autowired
	SolicitacaoRepository solicitacaoRepository;
	
	@Override
	public void save(Solicitacao solicitacao) {
		solicitacaoRepository.save(solicitacao);
	}
	
	@Override
	public Solicitacao criarSolicitacao(Usuario usuarioRemetente, Usuario usuarioDestinatario, TemaTcc temaTcc) {
		Solicitacao solicitacao = new Solicitacao(usuarioRemetente, usuarioDestinatario, temaTcc);
		return solicitacao;
	}
	
	@Override
	public Solicitacao atualizarSolicitacao(boolean decisao, String justificativa, Solicitacao solicitacao) {
		solicitacao.setDecisao(decisao);
    	solicitacao.setJustificativa(justificativa);
    	return solicitacao;
	}
	
	@Override
	public Optional<Solicitacao> getById(Long id) {
		return solicitacaoRepository.findById(id);
	}

	@Override
	public void removerSolicitacao(Solicitacao solicitacao) {
		solicitacaoRepository.delete(solicitacao);
	}

	@Override
	public void deleteAllByTemaTcc(TemaTcc temaTcc) {
		List<Solicitacao> solicitacoes = solicitacaoRepository.findAllByTemaTcc(temaTcc);
		for (Solicitacao solicitacao: solicitacoes) {
			this.removerSolicitacao(solicitacao);
		}
	}

	@Override
	public List<SolicitacaoDTO> getSolicitacoesRecebidasDTO(Usuario usuario) {
		UsuarioCadastradoDTO usuarioCadastradoDTO = new UsuarioCadastradoDTO(usuario.getNome(), usuario.getUsername(), usuario.getEmail(), usuario.getTipoUsuario());
		List<Solicitacao> solicitacoes = solicitacaoRepository.findAllByUsuarioDestinatario(usuario);
		List<SolicitacaoDTO> solicitacoesDTO = new ArrayList<>();

		for (Solicitacao solicitacao : solicitacoes) {
			UsuarioCadastradoDTO usuarioRemetenteDTO = new UsuarioCadastradoDTO(solicitacao.getUsuarioRemetente().getNome(), solicitacao.getUsuarioRemetente().getUsername(),
					solicitacao.getUsuarioRemetente().getEmail(), solicitacao.getUsuarioRemetente().getTipoUsuario());
			SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO(solicitacao.getId(), usuarioCadastradoDTO, usuarioRemetenteDTO, solicitacao.isAprovado(), solicitacao.getJustificativa());
			solicitacoesDTO.add(solicitacaoDTO);
		}
		return solicitacoesDTO;
	}

}
