package com.ufcg.psoft.tccmatch.controller;

import com.ufcg.psoft.tccmatch.DTO.OrientacaoCadastradaDTO;
import com.ufcg.psoft.tccmatch.DTO.OrientacaoComAreaDTO;
import com.ufcg.psoft.tccmatch.DTO.OrientacaoDTO;
import com.ufcg.psoft.tccmatch.DTO.ProblemaOrientacaoDTO;
import com.ufcg.psoft.tccmatch.DTO.RelatorioOrientacoesComAreaDTO;
import com.ufcg.psoft.tccmatch.DTO.RelatorioProblemaGeralDTO;
import com.ufcg.psoft.tccmatch.model.*;
import com.ufcg.psoft.tccmatch.service.*;
import com.ufcg.psoft.tccmatch.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class OrientacaoController {

	@Autowired
	CoordenadorService coordenadorService;

	@Autowired
	ProfessorService professorService;

	@Autowired
	AlunoService alunoService;

	@Autowired
	OrientacaoService orientacaoService;

	@Autowired
	TemaTccService temaTccService;

	@Autowired
	ProblemaOrientacaoService problemaOrientacaoService;
	
	@Autowired
	SolicitacaoService solicitacaoService;

	@Autowired
	private Map<String, UsuarioService> services;

	@RequestMapping(value = "/orientacao/{tokenCoordenador}", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarOrientacao(@RequestBody OrientacaoDTO orientacaoDTO,
			UriComponentsBuilder ucBuilder, @PathVariable("tokenCoordenador") Long idCoordenador) {

		Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);

		if (coordenadorOp.isEmpty()) {
			return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
		}

		Optional<Aluno> alunoOp = alunoService.findByUsername(orientacaoDTO.getMatriculaAluno().toString());

		if (alunoOp.isEmpty()) {
			return ErroAluno.erroAlunoNaoEncontradoMatricula(orientacaoDTO.getMatriculaAluno());
		}

		Aluno aluno = alunoOp.get();
		Optional<Professor> professorOp = professorService.findByUsername(orientacaoDTO.getCpfProfessor().toString());

		if (professorOp.isEmpty()) {
			return ErroProfessor.erroProfessorNaoEncontradoCpf(orientacaoDTO.getCpfProfessor());
		}

		Professor professor = professorOp.get();
		Optional<TemaTcc> temaTccOp = temaTccService.getByTitulo(orientacaoDTO.getTituloTema());

		if (temaTccOp.isEmpty()) {
			return ErroTemaTcc.erroTemaNaoCadastrado(orientacaoDTO.getTituloTema());
		}

		TemaTcc temaTcc = temaTccOp.get();

		if (!temaTccService.verificaCriadorTema(temaTcc, aluno, professor)) {
			return ErroTemaTcc.erroTemaCriadorAusente(orientacaoDTO.getTituloTema());
		}

		Optional<Orientacao> orientacaoOp = orientacaoService.findOrientacaoByAluno(aluno);

		if (orientacaoOp.isPresent()) {
			return ErroOrientacao.alunoJaTemOrientacao(aluno.getNome());
		}

		Orientacao orientacao = orientacaoService.cadastrarOrientacao(aluno, temaTcc, professor,
				orientacaoDTO.getSemestre());
		orientacaoService.save(orientacao);

		OrientacaoCadastradaDTO orientacaoCadastradaDTO = new OrientacaoCadastradaDTO(orientacao.getId(),
				aluno.getUsername(), professor.getUsername(),
				temaTcc.getTitulo(), orientacao.getSemestre(), orientacao.isFinalizada());
		
		solicitacaoService.deleteAllByTemaTcc(temaTcc);

		return new ResponseEntity<OrientacaoCadastradaDTO>(orientacaoCadastradaDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/problemaOrientacao/{token}/{tipoUsuario}", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarProblemaOrientacao(@PathVariable("token") Long id,
			@PathVariable("tipoUsuario") String tipoUsuario, @RequestBody ProblemaOrientacaoDTO problemaOrientacaoDTO,
			UriComponentsBuilder ucBuilder) { // TODO Verificar se a orientação passada é daquele aluno.

		UsuarioService usuarioService = services.get(tipoUsuario.toUpperCase());

		if (usuarioService == null) {
			return ErroLogin.erroServiceIndisponivel(tipoUsuario);
		}

		Optional<Usuario> usuarioOp = usuarioService.getById(id);

		if (usuarioOp.isEmpty()) {
			return ErroLogin.erroTokenInvalido(id);
		}

		Usuario usuario = usuarioOp.get();
		Optional<Orientacao> orientacaoOp = orientacaoService
				.getOrientacaoById(problemaOrientacaoDTO.getIdOrientacao());

		if (orientacaoOp.isEmpty()) {
			return ErroOrientacao.orientacaoNaoCadastrada(problemaOrientacaoDTO.getIdOrientacao());
		}

		Orientacao orientacao = orientacaoOp.get();

		ProblemaOrientacao problemaOrientacao = problemaOrientacaoService.criarProblemaOrientacao(usuario,
				problemaOrientacaoDTO.getDescricaoProblema(), orientacao);
		problemaOrientacaoService.save(problemaOrientacao);

		return ReturnMessage.cadastroProblema(tipoUsuario, usuario.getNome(), orientacao.getId());
	}

	@RequestMapping(value = "/problemasNoPeriodo/{tokenCoordenador}/{periodo}", method = RequestMethod.GET)
	public ResponseEntity<?> listarProblemasNoPeriodo(@PathVariable("tokenCoordenador") Long idCoordenador,
			@PathVariable("periodo") String periodo) {

		Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);

		if (coordenadorOp.isEmpty()) {
			return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
		}

		List<Orientacao> orientacoesDoPeriodo = orientacaoService.findAllOrientacaoBySemestre(periodo);

		RelatorioProblemaGeralDTO relatorioProblemaGeralDTO = problemaOrientacaoService
				.gerarRelatorioProblemaGeralDTO(orientacoesDoPeriodo);

		return new ResponseEntity<RelatorioProblemaGeralDTO>(relatorioProblemaGeralDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/finalizarOrientacao/{tokenCoordenador}/{idOrientacao}", method = RequestMethod.POST)
	public ResponseEntity<?> finalizarOrientacao(@PathVariable("tokenCoordenador") Long idCoordenador,
			@PathVariable("idOrientacao") Long idOrientacao) {

		Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);

		if (coordenadorOp.isEmpty()) {
			return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
		}

		Optional<Orientacao> orientacaoOp = orientacaoService.getOrientacaoById(idOrientacao);

		if (orientacaoOp.isEmpty()) {
			return ErroOrientacao.orientacaoNaoCadastrada(idOrientacao);
		}
		Orientacao orientacao = orientacaoOp.get();
		orientacao.setFinalizada(true);
		orientacaoService.save(orientacao);

		OrientacaoCadastradaDTO orientacaoDTO = new OrientacaoCadastradaDTO(orientacao.getId(),
				orientacao.getAluno().getUsername(), orientacao.getProfessor().getUsername(),
				orientacao.getTemaTcc().getTitulo(), orientacao.getSemestre(), orientacao.isFinalizada());

		return new ResponseEntity<OrientacaoCadastradaDTO>(orientacaoDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/orientacoesEmCurso/{tokenCoordenador}/{periodo}", method = RequestMethod.GET)
	public ResponseEntity<?> listarOrientacoesEmCurso(@PathVariable("tokenCoordenador") Long idCoordenador,
			@PathVariable("periodo") String periodo) {

		Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);

		if (coordenadorOp.isEmpty()) {
			return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
		}

		List<Orientacao> orientacoesEmCurso = orientacaoService.findAllEmCursoBySemestre(periodo);

		List<OrientacaoCadastradaDTO> listaOrientacoesEmCurso = orientacaoService
				.listarOrientacoesEmCursoDTO(orientacoesEmCurso);

		return new ResponseEntity<List<OrientacaoCadastradaDTO>>(listaOrientacoesEmCurso, HttpStatus.OK);
	}

	@RequestMapping(value = "/orientacoesFinalizadas/{tokenCoordenador}/{periodo}", method = RequestMethod.GET)
	public ResponseEntity<?> listarOrientacoesFinalizadas(@PathVariable("tokenCoordenador") Long idCoordenador,
			@PathVariable("periodo") String periodo) {

		Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);

		if (coordenadorOp.isEmpty()) {
			return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
		}

		List<Orientacao> orientacoesFinalizadas = orientacaoService.findAllFinalizadasBySemestre(periodo);

		List<OrientacaoCadastradaDTO> listaOrientacoesFinalizadas = orientacaoService
				.listarOrientacoesFinalizadasDTO(orientacoesFinalizadas);

		return new ResponseEntity<List<OrientacaoCadastradaDTO>>(listaOrientacoesFinalizadas, HttpStatus.OK);
	}

	@RequestMapping(value = "/orientacoesComAreas/{tokenCoordenador}/{periodo}", method = RequestMethod.GET)
	public ResponseEntity<?> gerarRelatorioOrientacoesComArea(@PathVariable("tokenCoordenador") Long idCoordenador,
			@PathVariable("periodo") String periodo) {

		Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);

		if (coordenadorOp.isEmpty()) {
			return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
		}

		List<Orientacao> orientacoesFinalizadas = orientacaoService.findAllFinalizadasBySemestre(periodo);
		List<Orientacao> orietacoesEmCurso = orientacaoService.findAllEmCursoBySemestre(periodo);

		List<OrientacaoComAreaDTO> orientacoesFinalizadasDTO = orientacaoService
				.gerarOrientacoesComAreaDTO(orientacoesFinalizadas);
		List<OrientacaoComAreaDTO> orientacoesEmCursoDTO = orientacaoService
				.gerarOrientacoesComAreaDTO(orietacoesEmCurso);

		RelatorioOrientacoesComAreaDTO relatorioOrientacoesComAreaDTO = new RelatorioOrientacoesComAreaDTO(
				orientacoesEmCursoDTO, orientacoesFinalizadasDTO);

		return new ResponseEntity<RelatorioOrientacoesComAreaDTO>(relatorioOrientacoesComAreaDTO, HttpStatus.OK);
	}

}
