package com.ufcg.psoft.tccmatch.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.model.Notificacao;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;
import com.ufcg.psoft.tccmatch.repository.NotificacaoRepository;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {
	
	@Autowired
	private NotificacaoRepository notificacaoRepository;
	
	@Autowired
	private AlunoService alunoService;
	
	@Override
	public void save(Notificacao notificacao) {
		this.notificacaoRepository.save(notificacao);
	}
	
	@Override
	public List<String> listaNotificacoesUsuario(Usuario usuario) {
		List<Notificacao> listaNotificacoes = usuario.getNotificacoes();
		List<String> listaRetorno = new ArrayList<String>();
		
		for (Notificacao notificacao : listaNotificacoes) {
			notificacao.setRead(true);
			this.save(notificacao);
			listaRetorno.add(notificacao.toString());
		}
		
		usuario.limparNotificacoes();
		
		return listaRetorno;
	}

	@Override
	public void notificaAlunoNovoTemaTcc(TemaTcc temaTcc) {
		String contentNotificacao = "O tema Tcc " + temaTcc.getTitulo() + " nas suas áreas de interesse foi cadastrado";
		Notificacao notificacao = new Notificacao(contentNotificacao, ""); // TODO COLOCAR EMAIL
		this.save(notificacao);
		
		List<Aluno> alunos = alunoService.findAll();
		List<AreaDeEstudo> areasTcc = temaTcc.getAreasDeEstudoRelacionadas();
		
		for (Aluno aluno : alunos) {
			for (AreaDeEstudo areaDeEstudo : aluno.getAreasDeEstudo()) {
				if (areasTcc.contains(areaDeEstudo)) {
					aluno.addNotificacao(notificacao);
					alunoService.save(aluno);					
					break;
				}
			}
		}
	}

	@Override
	public void notificaAlunoInteresseProfessorTema(TemaTcc temaTcc, String nomeProfessor, Aluno aluno) {
		String contentNotificacao = "O professor " + nomeProfessor + " manifestou interesse no seu tema de tcc " + temaTcc;
		
		//TODO agrupar esse trecho de código em um método privado
		Notificacao notificacao = new Notificacao(contentNotificacao, ""); // TODO COLOCAR EMAIL
		this.save(notificacao);
		aluno.addNotificacao(notificacao);
		alunoService.save(aluno);
	}
}
