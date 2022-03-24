package com.ufcg.psoft.tccmatch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Notificacao;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.repository.NotificacaoRepository;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {
	
	@Autowired
	private NotificacaoRepository notificacaoRepository;
	
	@Autowired
	private AlunoService alunoService;

	@Override
	public void notificaProfessorParaAluno(TemaTcc temaTcc, List<Aluno> alunos) {
		String contentNotificacao = "O tema Tcc " + temaTcc + " nas suas áreas de interesse foi cadastrado";
		
		for (Aluno aluno : alunos) {
			Notificacao notificacao = new Notificacao(contentNotificacao, false);
			this.save(notificacao);
			aluno.addNotificacao(notificacao);
			alunoService.save(aluno);
		}
	}

	@Override
	public void save(Notificacao notificacao) {
		this.notificacaoRepository.save(notificacao);
	}

}
