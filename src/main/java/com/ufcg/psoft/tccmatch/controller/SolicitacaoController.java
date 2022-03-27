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
import com.ufcg.psoft.tccmatch.model.Solicitacao;
import com.ufcg.psoft.tccmatch.model.TemaInteresse;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;
import com.ufcg.psoft.tccmatch.service.AlunoService;
import com.ufcg.psoft.tccmatch.service.NotificacaoService;
import com.ufcg.psoft.tccmatch.service.ProfessorService;
import com.ufcg.psoft.tccmatch.service.SolicitacaoService;
import com.ufcg.psoft.tccmatch.service.TemaInteresseService;
import com.ufcg.psoft.tccmatch.service.TemaTccService;
import com.ufcg.psoft.tccmatch.service.UsuarioService;
import com.ufcg.psoft.tccmatch.util.ErroAluno;
import com.ufcg.psoft.tccmatch.util.ErroLogin;
import com.ufcg.psoft.tccmatch.util.ErroProfessor;
import com.ufcg.psoft.tccmatch.util.ErroSolicitacao;
import com.ufcg.psoft.tccmatch.util.ErroTemaInteresse;
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
	SolicitacaoService solicitacaoService;
	
	@Autowired
	TemaInteresseService temaInteresseService;
	
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
		Optional<Professor> professorOp = temaTccService.getProfessorByTema(temaTcc);
		
		if (professorOp.isEmpty()) {
			return ErroTemaTcc.erroTemaNaoProfessor(tituloTemaTcc);
		}
		
		Professor professor = professorOp.get();
		Solicitacao solicitacao = solicitacaoService.criarSolicitacao(aluno, professor, temaTcc);
		solicitacaoService.save(solicitacao);
		
		notificacaoService.notificaProfessorSolicitacaoAluno(temaTcc, aluno);
		
		// TODO Verificar se aluno está em uma orientação

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
		Optional<Aluno> alunoOp = temaTccService.getAlunoByTema(temaTcc);
		
		if (alunoOp.isEmpty()) {
			return ErroTemaTcc.erroTemaNaoAluno(tituloTemaTcc);
		}
		
		Aluno aluno = alunoOp.get();
		Solicitacao solicitacao = solicitacaoService.criarSolicitacao(professor, aluno, temaTcc);
		solicitacaoService.save(solicitacao);
				
		notificacaoService.notificaAlunoInteresseProfessorTema(temaTcc, professor);
		
		// TODO Verificar se aluno está em uma orientação

		return ReturnMessage.solicitacaoEnviada();
	}
	
	@RequestMapping(value = "/decisaoSolicitacao/{token}/{tipoUsuario}/{idSolicitacao}", method = RequestMethod.PUT)
	public ResponseEntity<?> decisaoSolicitacao(@PathVariable("token") long id, @PathVariable("tipoUsuario") String tipoUsuario, 
												@PathVariable("idSolicitacao") long idSolicitacao,  
												@RequestBody boolean decisao, String justificativa) {
		
		UsuarioService usuarioService = services.get(tipoUsuario.toUpperCase());

    	if (usuarioService == null) {
    		return ErroLogin.erroServiceIndisponivel(tipoUsuario);
    	}
    	
    	Optional<Usuario> usuarioOp = usuarioService.getById(id);

    	if (usuarioOp.isEmpty()) {
    		return ErroLogin.erroTokenInvalido(id);
    	}

    	Usuario usuario = usuarioOp.get();
    	Optional<Solicitacao> solicitacaoOp = solicitacaoService.getById(idSolicitacao);

    	if (solicitacaoOp.isEmpty()) {
    		return ErroSolicitacao.erroSolicitacaoNaoEncontrada(idSolicitacao);
    	}

    	Solicitacao solicitacao = solicitacaoService.atualizarSolicitacao(decisao, justificativa, solicitacaoOp.get());
    	solicitacaoService.save(solicitacao);
    
		if (decisao && usuario.isProfessor()) {
			Professor professor = professorService.getById(usuario.getId()).get();
			
			if (!professor.isDisponivel()) {
				return ErroProfessor.erroProfessorQuotaInsuficiente(id);
			} else {
				professorService.configurarQuota(professor, professor.getQuota() - 1);
				professorService.save(professor);
			}
		}
		
		notificacaoService.notificaCoordenadorSolicitacaoAceita(solicitacao);

		return ReturnMessage.decisaoSolicitacao(decisao, idSolicitacao);
	}
	
//	@RequestMapping(value = "/decisaoInteresse/{tokenAluno}/{idTemaInteresse}", method = RequestMethod.PUT)
//	public ResponseEntity<?> decisaoInteresse(@PathVariable("tokenAluno") long idAluno, 
//										      @PathVariable("idTemaInteresse") long idTemaInteresse,  @RequestBody boolean decisao) {
//		
//		Optional<Aluno> alunoOp = alunoService.getById(idAluno);
//    	
//    	if (alunoOp.isEmpty()) {
//    		return ErroAluno.erroAlunoNaoEncontrado(idAluno);
//    	}
//		
//    	Optional<TemaInteresse> temaInteresseOp = temaInteresseService.getById(idTemaInteresse);
//    	
//    	if (temaInteresseOp.isEmpty()) {
//    		return ErroTemaInteresse.erroTemaInteresseNaoEncontrado(idTemaInteresse);
//    	}
//
//		Aluno aluno = alunoOp.get();
//    	TemaInteresse temaInteresse = temaInteresseOp.get();
//    	
//    	
//		Professor professor = temaInteresse.getProfessorInteressado();
//		
//		if (decisao && professor.getQuota() < 1) {
//			return ErroProfessor.erroProfessorQuotaInsuficiente(professor.getId());
//		}
//    	
//    	temaInteresse.setAprovado(decisao);
//    	temaInteresseService.save(temaInteresse);
//    	
//    	if (decisao) {
//    		professor.setQuota(professor.getQuota() - 1);
//    		professorService.save(professor);
//			notificacaoService.notificaCoordenadorConfirmacaoInteresse(temaInteresse);
//		}
//
//		return ReturnMessage.decisaoSolicitacao(decisao, idTemaInteresse);
//	}
	
	@RequestMapping(value = "/solicitacao/{token}/{tipoUsuario}", method = RequestMethod.GET)
	public ResponseEntity<?> listarSolicitacoes(@PathVariable("token") long id, @PathVariable("tipoUsuario") String tipoUsuario) {
		
		UsuarioService usuarioService = services.get(tipoUsuario.toUpperCase());

    	if (usuarioService == null) {
    		return ErroLogin.erroServiceIndisponivel(tipoUsuario);
    	}
    	
    	Optional<Usuario> usuarioOp = usuarioService.getById(id);

    	if (usuarioOp.isEmpty()) {
    		return ErroLogin.erroTokenInvalido(id);
    	}
    	
    	Usuario usuario = usuarioOp.get();
    	List<Solicitacao> solicitacoes = solicitacaoService.getSolicitacoesRecebidas(usuario);
    	
    	return new ResponseEntity<List<Solicitacao>>(solicitacoes, HttpStatus.OK);
	}
}
