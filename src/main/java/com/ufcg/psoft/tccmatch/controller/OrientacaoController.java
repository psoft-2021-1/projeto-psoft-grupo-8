package com.ufcg.psoft.tccmatch.controller;

import com.ufcg.psoft.tccmatch.DTO.ListarOrientacoesCadastradasDTO;
import com.ufcg.psoft.tccmatch.DTO.OrientacaoDTO;
import com.ufcg.psoft.tccmatch.DTO.ProblemaOrientacaoDTO;
import com.ufcg.psoft.tccmatch.model.*;
import com.ufcg.psoft.tccmatch.service.*;
import com.ufcg.psoft.tccmatch.util.ErroAluno;
import com.ufcg.psoft.tccmatch.util.ErroCoordenador;
import com.ufcg.psoft.tccmatch.util.ErroLogin;
import com.ufcg.psoft.tccmatch.util.ErroOrientacao;
import com.ufcg.psoft.tccmatch.util.ErroProfessor;
import com.ufcg.psoft.tccmatch.util.ErroTemaTcc;
import com.ufcg.psoft.tccmatch.util.ReturnMessage;

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
	private Map<String, UsuarioService> services;

	@RequestMapping(value = "/orientacao/{tokenCoordenador}", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarOrientacao(@RequestBody OrientacaoDTO orientacaoDTO,
			UriComponentsBuilder ucBuilder, @PathVariable("tokenCoordenador") long idCoordenador) {

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

		if (!temaTcc.getUsernameCriador().equals(professor.getUsername())
				&& !temaTcc.getUsernameCriador().equals(aluno.getUsername())) {
			return ErroTemaTcc.erroTemaCriadorAusente(orientacaoDTO.getTituloTema());
		}

		List<Orientacao> orientacoesAluno = orientacaoService.findAllOrientacaoByAluno(aluno);

		if (orientacoesAluno.size() == 1) {
			return ErroOrientacao.alunoJaTemOrientacao(aluno.getNome());
		}

		Orientacao orientacao = orientacaoService.cadastrarOrientacao(aluno, temaTcc, professor,
				orientacaoDTO.getSemestre());
		orientacaoService.save(orientacao);

		ListarOrientacoesCadastradasDTO novaOrientacaoDTO = new ListarOrientacoesCadastradasDTO(orientacao.getId(),
				orientacao.getAluno().getUsername(), orientacao.getTemaTcc().getTitulo(), orientacao.getSemestre());

		return new ResponseEntity<ListarOrientacoesCadastradasDTO>(novaOrientacaoDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/cadastrarProblemaOrientacao/{token}/{tipoUsuario}", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarProblemaOrientacao(@PathVariable("token") long id,
			@PathVariable("tipoUsuario") String tipoUsuario, @RequestBody ProblemaOrientacaoDTO problemaOrientacaoDTO,
			UriComponentsBuilder ucBuilder) {

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
			return ErroOrientacao.orientacaoNaoExiste(problemaOrientacaoDTO.getIdOrientacao());
		}

		Orientacao orientacao = orientacaoOp.get();

		if (orientacao.getProblemaOrientacao() != null) {
			return ErroOrientacao.orientacaoJaTemProblema(orientacao.getId());
		}

		ProblemaOrientacao problemaOrientacao = problemaOrientacaoService.criarProblemaOrientacao(usuario.getNome(),
				problemaOrientacaoDTO.getDescricaoProblema());
		problemaOrientacaoService.save(problemaOrientacao);

		orientacao.setProblemaOrientacao(problemaOrientacao);
		orientacaoService.save(orientacao);

		return ReturnMessage.cadastroProblema(tipoUsuario, usuario.getNome(), orientacao.getId());
	}
}
