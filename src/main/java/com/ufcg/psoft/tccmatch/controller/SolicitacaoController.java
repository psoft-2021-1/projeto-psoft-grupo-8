package com.ufcg.psoft.tccmatch.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;
import com.ufcg.psoft.tccmatch.service.AlunoService;
import com.ufcg.psoft.tccmatch.service.NotificacaoService;
import com.ufcg.psoft.tccmatch.service.ProfessorService;
import com.ufcg.psoft.tccmatch.service.SolicitacaoOrientacaoService;
import com.ufcg.psoft.tccmatch.service.TemaTccService;
import com.ufcg.psoft.tccmatch.service.UsuarioService;
import com.ufcg.psoft.tccmatch.util.ErroAluno;
import com.ufcg.psoft.tccmatch.util.ErroLogin;
import com.ufcg.psoft.tccmatch.util.ErroProfessor;
import com.ufcg.psoft.tccmatch.util.ErroSolicitacao;
import com.ufcg.psoft.tccmatch.util.ErroTemaTcc;
import com.ufcg.psoft.tccmatch.util.ReturnMessage;


@RestController
@RequestMapping("/api/")
@CrossOrigin
public class SolicitacaoController {
	
	@Autowired
	AlunoService alunoService;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	TemaTccService temaTccService;
	
	@Autowired
	NotificacaoService notificacaoService;
	
	@Autowired
	SolicitacaoOrientacaoService solicitacaoService;
	
	@Autowired
	private Map<String, UsuarioService> services;
	
	@RequestMapping(value = "/solicitacao/{tokenAluno}", method = RequestMethod.POST)
	public ResponseEntity<?> solicitarOrientacao(@PathVariable("tokenAluno") long tokenAluno, @RequestBody String tituloTemaTcc) {

		Optional<Aluno> alunoOp = alunoService.getById(tokenAluno);

		if (alunoOp.isEmpty()) {
			return ErroAluno.erroAlunoNaoEncontrado(tokenAluno);
		}
		
		Aluno aluno = alunoOp.get();		
		Optional<TemaTcc> temaTccOp = temaTccService.getByTitulo(tituloTemaTcc);

		if (temaTccOp.isEmpty()) {
			return ErroTemaTcc.erroTemaNaoCadastrado(tituloTemaTcc);
		}
		
		TemaTcc temaTcc = temaTccOp.get();
		Optional<Professor> professorOp = temaTccService.getProfessorTemaTcc(temaTcc);
		
		if (professorOp.isEmpty()) {
			return ErroTemaTcc.erroTemaNaoProfessor(tituloTemaTcc);
		}
		
		Professor professor = professorOp.get();
		SolicitacaoOrientacao solicitacao = solicitacaoService.criarSolicitacao(aluno.getUsername(), professor.getUsername(), temaTcc);
		solicitacaoService.save(solicitacao);
		
		notificacaoService.notificaProfessorSolicitacaoAluno(temaTcc, aluno);

		return ReturnMessage.solicitacaoEnviada();
	}
	
	@RequestMapping(value = "/manifestar/{tokenProfessor}", method = RequestMethod.POST)
	public ResponseEntity<?> manifestarInteresse(@PathVariable("tokenProfessor") long tokenProfessor, @RequestBody String tituloTemaTcc) {

		Optional<Professor> professorOp = professorService.getById(tokenProfessor);

		if (professorOp.isEmpty()) {
			return ErroProfessor.erroProfessorNaoEncontrado(tokenProfessor);
		}
		
		Professor professor = professorOp.get();
		Optional<TemaTcc> temaTccOp = temaTccService.getByTitulo(tituloTemaTcc);

		if (temaTccOp.isEmpty()) {
			return ErroTemaTcc.erroTemaNaoCadastrado(tituloTemaTcc);
		}
		
		TemaTcc temaTcc = temaTccOp.get();
		Optional<Aluno> alunoOp = temaTccService.getAlunoTemaTcc(temaTcc);
		
		if (alunoOp.isEmpty()) {
			return ErroTemaTcc.erroTemaNaoAluno(tituloTemaTcc);
		}
		
		Aluno aluno = alunoOp.get();		
		SolicitacaoOrientacao solicitacao = solicitacaoService.criarSolicitacao(professor.getUsername(), aluno.getUsername(), temaTcc);
		solicitacaoService.save(solicitacao);

		notificacaoService.notificaAlunoInteresseProfessorTema(temaTcc, professor);

		return ReturnMessage.solicitacaoEnviada();
	}
	
	@RequestMapping(value = "/decisaoSolicitacao/{token}/{tipoUsuario}/{idSolicitacao}", method = RequestMethod.PUT)
	public ResponseEntity<?> decisaoSolicitacao(@PathVariable("token") long id, @PathVariable String tipoUsuario,
												@PathVariable("idSolicitacao") long idSolicitacao, @RequestBody boolean decisao) {
		
		UsuarioService usuarioService = services.get(tipoUsuario.toUpperCase());
    	
    	if (usuarioService == null) {
    		return ErroLogin.erroServiceIndisponivel(tipoUsuario);
    	}

		Optional<Usuario> usuarioOp = usuarioService.getById(id);

		if (usuarioOp.isEmpty()) {
			return ErroLogin.erroTokenInvalido(id);
		}
		
    	Optional<SolicitacaoOrientacao> solicitacaoOp = solicitacaoService.getById(idSolicitacao);
    	
    	if (solicitacaoOp.isEmpty()) {
    		return ErroSolicitacao.erroSolicitacaoNaoEncontrada(idSolicitacao);
    	}

		Usuario usuarioDestinatario = usuarioOp.get();
    	SolicitacaoOrientacao solicitacao = solicitacaoOp.get();
    	solicitacao.setAprovado(decisao);
    	solicitacaoService.save(solicitacao);
    	
    	// TODO Notificação para coordenador caso decisão seja true
		if (solicitacao.isAprovado()) {
			notificacaoService.notificaCoordenadorSolicitacaoAceita(solicitacao, usuarioDestinatario);
		}

		return ReturnMessage.decisaoSolicitacao(decisao, idSolicitacao);
	}
	
	@RequestMapping(value = "/solicitacao/{token}/{tipoUsuario}", method = RequestMethod.GET)
	public ResponseEntity<?> listarSolicitacaoes(@PathVariable("token") long id, @PathVariable String tipoUsuario) {
		
		UsuarioService usuarioService = services.get(tipoUsuario.toUpperCase());
    	
    	if (usuarioService == null) {
    		return ErroLogin.erroServiceIndisponivel(tipoUsuario);
    	}

    	Optional<Usuario> usuarioOp = usuarioService.getById(id);
    	
    	if (usuarioOp.isEmpty()) {
    		return ErroLogin.erroTokenInvalido(id);
    	}
    	
    	Usuario usuario = usuarioOp.get();
    	List<SolicitacaoOrientacao> solicitacoes = solicitacaoService.getSolicitacoesRecebidas(usuario.getUsername());
    	
    	return new ResponseEntity<List<SolicitacaoOrientacao>>(solicitacoes, HttpStatus.OK);
	}
}
