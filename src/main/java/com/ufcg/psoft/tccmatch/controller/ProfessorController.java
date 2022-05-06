package com.ufcg.psoft.tccmatch.controller;

import com.ufcg.psoft.tccmatch.DTO.*;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.model.Orientacao;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.service.*;
import com.ufcg.psoft.tccmatch.util.ErroAreaDeEstudo;
import com.ufcg.psoft.tccmatch.util.ErroProfessor;
import com.ufcg.psoft.tccmatch.util.ErroTemaTcc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class ProfessorController {

	@Autowired
	ProfessorService professorService;

	@Autowired
	AreaDeEstudoService areaDeEstudoService;

	@Autowired
	TemaTccService temaTccService;

	@Autowired
	NotificacaoService notificacaoService;

	@Autowired
	AlunoService alunoService;

	@Autowired
	OrientacaoService orientacaoService;

	@RequestMapping(value = "/professor/areaDeEstudo/{tokenProfessor}", method = RequestMethod.POST)
	public ResponseEntity<?> selecionarAreasDeEstudo(@RequestBody AreasSelecionadasDTO areasSelecionadasDTO,
			UriComponentsBuilder ucBuilder, @PathVariable("tokenProfessor") long idProfessor) {

		Optional<Professor> professorOp = professorService.getById(idProfessor);

		if (professorOp.isEmpty()) {
			return ErroProfessor.erroProfessorNaoEncontrado(idProfessor);
		}

		String areaDeEstudoNaoCadastrada = areaDeEstudoService
				.verificaAreasDeEstudo(areasSelecionadasDTO.getAreasDeEstudo());

		if (!areaDeEstudoNaoCadastrada.isEmpty()) {
			return ErroAreaDeEstudo.erroAreaDeEstudoNaoCadastrada(areaDeEstudoNaoCadastrada);
		}

		Professor professor = professorOp.get();
		List<AreaDeEstudo> areasDeEstudo = areaDeEstudoService.getAreasByNome(areasSelecionadasDTO.getAreasDeEstudo());

		professor.setAreasDeEstudo(areasDeEstudo);
		professorService.save(professor);

		ProfessorDisponivelDTO professorDisponivelDTO = new ProfessorDisponivelDTO(professor.getEmail(),
				professor.getNome(), professor.getAreasDeEstudo());

		return new ResponseEntity<ProfessorDisponivelDTO>(professorDisponivelDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/professor/temaTCC/{tokenProfessor}", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarTemaTcc(@RequestBody TemaTccProfessorDTO temaTccDTO,
			UriComponentsBuilder ucBuilder, @PathVariable("tokenProfessor") long idProfessor) {

		Optional<Professor> professorOp = professorService.getById(idProfessor);

		if (professorOp.isEmpty()) {
			return ErroProfessor.erroProfessorNaoEncontrado(idProfessor);
		}

		Optional<TemaTcc> temaTccOp = temaTccService.getByTitulo(temaTccDTO.getTitulo());

		if (!temaTccOp.isEmpty()) {
			return ErroTemaTcc.erroTemaJaCadastrado(temaTccDTO.getTitulo());
		}

		Professor professor = professorOp.get();
		String areaDeEstudoNaoCadastrada = areaDeEstudoService.verificaAreasDeEstudo(temaTccDTO.getAreasDeEstudoRelacionadas());
		
		if (!areaDeEstudoNaoCadastrada.isEmpty()) {
			return ErroAreaDeEstudo.erroAreaDeEstudoNaoCadastrada(areaDeEstudoNaoCadastrada);
		}

		TemaTcc temaTcc = temaTccService.criarTemaTccProfessor(temaTccDTO, professor);
		temaTccService.save(temaTcc);

		notificacaoService.notificaAlunoNovoTemaTcc(temaTcc);
		
		TemaTccCadastradoDTO temaTccCadastradoDTO = temaTccService.criarTemaTccCadastradoDTO(temaTcc);

		return new ResponseEntity<TemaTccCadastradoDTO>(temaTccCadastradoDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/professor/quota/{tokenProfessor}", method = RequestMethod.POST)
	public ResponseEntity<?> configurarQuota(@RequestBody Integer quota,
			@PathVariable("tokenProfessor") long idProfessor) {

		Optional<Professor> professorOp = professorService.getById(idProfessor);

		if (professorOp.isEmpty()) {
			return ErroProfessor.erroProfessorNaoEncontrado(idProfessor);
		}

		Professor professor = professorOp.get();

		professor.setQuota(quota);
		professorService.save(professor);

		ProfessorCadastradoDTO professorCadastradoDTO = new ProfessorCadastradoDTO(professor.getNome(),
				professor.getUsername(), professor.getEmail(), professor.getQuota(), professor.getLaboratorios());

		return new ResponseEntity<ProfessorCadastradoDTO>(professorCadastradoDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/professor/temasTccCadastrei/{tokenProfessor}", method = RequestMethod.GET)
	public ResponseEntity<?> listarTemasTccCadastradosProfessor(@PathVariable("tokenProfessor") long idProfessor) {

		Optional<Professor> professorOp = professorService.getById(idProfessor);

		if (professorOp.isEmpty()) {
			return ErroProfessor.erroProfessorNaoEncontrado(idProfessor);
		}

		List<TemaTcc> listaTemasTcc = temaTccService.getTemasTccProfessor(professorOp.get().getId());
		List<TemaTccCadastradoDTO> listaTemasTccDTO = temaTccService.getTemasTccDTO(listaTemasTcc);

		return new ResponseEntity<List<TemaTccCadastradoDTO>>(listaTemasTccDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/aluno/temasTccAlunos/{tokenProfessor}", method = RequestMethod.GET)
	public ResponseEntity<?> listarTemasTccAlunos(@PathVariable("tokenProfessor") long tokenProfessor) {

		Optional<Professor> professorOp = professorService.getById(tokenProfessor);

		if (professorOp.isEmpty()) {
			return ErroProfessor.erroProfessorNaoEncontrado(tokenProfessor);
		}

		List<TemaTcc> listaTemasTcc = temaTccService.getTemasTccAlunos();
		List<TemaTccCadastradoDTO> listaTemasTccDTO = temaTccService.getTemasTccDTO(listaTemasTcc);

		return new ResponseEntity<List<TemaTccCadastradoDTO>>(listaTemasTccDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/professor/listarOrientacacoesTccCadastradas/{tokenProfessor}", method = RequestMethod.GET)
	public ResponseEntity<?> listarOrientacoesCadastradas(@PathVariable("tokenProfessor") long idProfessor) {
		Optional<Professor> professorOp = professorService.getById(idProfessor);

		if (professorOp.isEmpty()) {
			return ErroProfessor.erroProfessorNaoEncontrado(idProfessor);
		}
		Professor professor = professorOp.get();
		List<Orientacao> listaOrientacoesProfessor = orientacaoService.findAllOrientacaoByProfessor(professor);

		List<OrientacaoCadastradaDTO> listaRetorno = new ArrayList<>();

		for (Orientacao orientacao : listaOrientacoesProfessor) {
			OrientacaoCadastradaDTO orientacaoDTO = new OrientacaoCadastradaDTO(orientacao.getId(),
					orientacao.getAluno().getUsername(), orientacao.getAluno().getUsername(), orientacao.getTemaTcc().getTitulo(), orientacao.getSemestre(), orientacao.isFinalizada());
			listaRetorno.add(orientacaoDTO);
		}

		return new ResponseEntity<List<OrientacaoCadastradaDTO>>(listaRetorno, HttpStatus.OK);
	}
}
