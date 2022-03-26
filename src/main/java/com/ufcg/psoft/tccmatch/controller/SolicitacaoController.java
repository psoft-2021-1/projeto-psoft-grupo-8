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
import com.ufcg.psoft.tccmatch.model.TemaInteresse;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.model.Usuario;
import com.ufcg.psoft.tccmatch.service.AlunoService;
import com.ufcg.psoft.tccmatch.service.NotificacaoService;
import com.ufcg.psoft.tccmatch.service.ProfessorService;
import com.ufcg.psoft.tccmatch.service.SolicitacaoOrientacaoService;
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
	SolicitacaoOrientacaoService solicitacaoService;
	
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
		Optional<Professor> professorOp = temaTccService.getProfessorTemaTcc(temaTcc);
		
		if (professorOp.isEmpty()) {
			return ErroTemaTcc.erroTemaNaoProfessor(tituloTemaTcc);
		}
		
		Professor professor = professorOp.get();
		SolicitacaoOrientacao solicitacaoOrientacao = solicitacaoService.criarSolicitacao(aluno, professor, temaTcc);
		solicitacaoService.save(solicitacaoOrientacao);
		
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
		
		TemaInteresse temaInteresse = temaInteresseService.manifestarInteresse(alunoOp.get(), professor, temaTcc);
		temaInteresseService.save(temaInteresse);
				
		notificacaoService.notificaAlunoInteresseProfessorTema(temaTcc, professor);

		return ReturnMessage.solicitacaoEnviada();
	}
	
	@RequestMapping(value = "/decisaoSolicitacao/{tokenProfessor}/{idSolicitacao}", method = RequestMethod.PUT)
	public ResponseEntity<?> decisaoSolicitacao(@PathVariable("tokenProfessor") long idProfessor, 
												@PathVariable("idSolicitacao") long idSolicitacao,  @RequestBody boolean decisao) {
		
		Optional<Professor> professorOp = professorService.getById(idProfessor);
    	
    	if (professorOp.isEmpty()) {
    		return ErroProfessor.erroProfessorNaoEncontrado(idProfessor);
    	}
		
    	Optional<SolicitacaoOrientacao> solicitacaoOp = solicitacaoService.getById(idSolicitacao);
    	
    	if (solicitacaoOp.isEmpty()) {
    		return ErroSolicitacao.erroSolicitacaoNaoEncontrada(idSolicitacao);
    	}

		Professor professor = professorOp.get();
		
		if (decisao && professor.getQuota() < 1) {
			return ErroProfessor.erroProfessorQuotaInsuficiente(idProfessor);
		}
		
    	SolicitacaoOrientacao solicitacaoOrientacao = solicitacaoOp.get();
    	solicitacaoOrientacao.setAprovado(decisao);
    	solicitacaoService.save(solicitacaoOrientacao);
    	
    	if (decisao) {
    		professor.setQuota(professor.getQuota() - 1);
    		professorService.save(professor);
    	}
    	
    	// TODO Notificação para coordenador caso decisão seja true
//		if (solicitacao.isAprovado()) {
//			notificacaoService.notificaCoordenadorSolicitacaoAceita(solicitacao, usuarioDestinatario);
//		}

		return ReturnMessage.decisaoSolicitacao(decisao, idSolicitacao);
	}
	
	@RequestMapping(value = "/decisaoInteresse/{tokenAluno}/{idTemaInteresse}", method = RequestMethod.PUT)
	public ResponseEntity<?> decisaoInteresse(@PathVariable("tokenAluno") long idAluno, 
												@PathVariable("idTemaInteresse") long idTemaInteresse,  @RequestBody boolean decisao) {
		
		Optional<Aluno> alunoOp = alunoService.getById(idAluno);
    	
    	if (alunoOp.isEmpty()) {
    		return ErroAluno.erroAlunoNaoEncontrado(idAluno);
    	}
		
    	Optional<TemaInteresse> temaInteresseOp = temaInteresseService.getById(idTemaInteresse);
    	
    	if (temaInteresseOp.isEmpty()) {
    		return ErroTemaInteresse.erroTemaInteresseNaoEncontrado(idTemaInteresse);
    	}
    	
    	//TODO caso ele aceite uma manifestacao de interesse, não está removendo as outras manifestações de interesses do mesmo tema.

		Aluno aluno = alunoOp.get();
    	TemaInteresse temaInteresse = temaInteresseOp.get();
    	
    	
		Professor professor = temaInteresse.getProfessorInteressado();
		
		if (decisao && professor.getQuota() < 1) {
			return ErroProfessor.erroProfessorQuotaInsuficiente(professor.getId());
		}
    	
    	temaInteresse.setAprovado(decisao);
    	temaInteresseService.save(temaInteresse);
    	
    	if (decisao) {
    		professor.setQuota(professor.getQuota() - 1);
    		professorService.save(professor);
    	}
    	
    	// TODO Notificação para coordenador caso decisão seja true
//		if (temaInteresse.isAprovado()) {
//			notificacaoService.notificaCoordenadorTemaInteresseAceito(temaInteresse, usuarioDestinatario);
//		}

		return ReturnMessage.decisaoSolicitacao(decisao, idTemaInteresse);
	}
	
	@RequestMapping(value = "/solicitacao/{tokenProfessor}/", method = RequestMethod.GET)
	public ResponseEntity<?> listarSolicitacaoes(@PathVariable("tokenProfessor") long idProfessor) {
		
		Optional<Professor> professorOp = professorService.getById(idProfessor);
    	
    	if (professorOp.isEmpty()) {
    		return ErroProfessor.erroProfessorNaoEncontrado(idProfessor);
    	}
    	
    	Professor professor = professorOp.get();
    	List<SolicitacaoOrientacao> solicitacoes = solicitacaoService.getSolicitacoesRecebidas(professor);
    	
    	return new ResponseEntity<List<SolicitacaoOrientacao>>(solicitacoes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/interesses/{tokenAluno}/", method = RequestMethod.GET)
	public ResponseEntity<?> listarInteresses(@PathVariable("tokenAluno") long idAluno) {
		
		Optional<Aluno> alunoOp = alunoService.getById(idAluno);
    	
    	if (alunoOp.isEmpty()) {
    		return ErroAluno.erroAlunoNaoEncontrado(idAluno);
    	}
    	
    	Aluno aluno = alunoOp.get();
    	
    	List<TemaInteresse> temaInteresse = temaInteresseService.getInteressesRecebidos(aluno);
    	
    	return new ResponseEntity<List<TemaInteresse>>(temaInteresse, HttpStatus.OK);
	}
}
