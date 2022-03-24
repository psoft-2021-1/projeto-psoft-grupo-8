package com.ufcg.psoft.tccmatch.controller;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.TemaTccAlunoDTO;
import com.ufcg.psoft.tccmatch.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.psoft.tccmatch.DTO.AreasSelecionadasDTO;
import com.ufcg.psoft.tccmatch.DTO.ProfessorDisponivelDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.service.AlunoService;
import com.ufcg.psoft.tccmatch.service.AreaDeEstudoService;
import com.ufcg.psoft.tccmatch.service.NotificacaoService;
import com.ufcg.psoft.tccmatch.service.TemaTccService;
import com.ufcg.psoft.tccmatch.util.ErroAluno;
import com.ufcg.psoft.tccmatch.util.ErroTemaTcc;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class AlunoController {

	@Autowired
	AlunoService alunoService;

	@Autowired
	AreaDeEstudoService areaDeEstudoService;

	@Autowired
	TemaTccService temaTccService;

	@Autowired
	ProfessorService professorService;
	
	@Autowired
	NotificacaoService notificacaoService;

	@RequestMapping(value = "/aluno/areaDeEstudo/{tokenAluno}", method = RequestMethod.POST)
	public ResponseEntity<?> selecionarAreasDeEstudo(@RequestBody AreasSelecionadasDTO areasSelecionadasDTO,
			UriComponentsBuilder ucBuilder, @PathVariable("tokenAluno") long idAluno) {

		Optional<Aluno> alunoOp = alunoService.getById(idAluno);

		if (alunoOp.isEmpty()) {
			return ErroAluno.erroAlunoNaoEncontrado(idAluno);
		}

		Aluno aluno = alunoOp.get();
		List<AreaDeEstudo> areasDeEstudo = areaDeEstudoService.getAreasByNome(areasSelecionadasDTO.getAreasDeEstudo());

		aluno.setAreasDeEstudo(areasDeEstudo);
		alunoService.save(aluno);

		return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
	}

	@RequestMapping(value = "/aluno/temaTCC/{tokenAluno}", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarTemaTcc(@RequestBody TemaTccAlunoDTO temaTccDTO, UriComponentsBuilder ucBuilder,
											  @PathVariable("tokenAluno") long tokenAluno) {

		Optional<Aluno> alunoOp = alunoService.getById(tokenAluno);

		if (alunoOp.isEmpty()) {
			return ErroAluno.erroAlunoNaoEncontrado(tokenAluno);
		}

		Optional<TemaTcc> temaTccOp = temaTccService.getByTitulo(temaTccDTO.getTitulo());

		if (!temaTccOp.isEmpty()) {
			return ErroTemaTcc.erroTemaJaCadastrado(temaTccDTO.getTitulo());
		}
		
		Aluno aluno = alunoOp.get();

		String areaDeEstudoNaoCadastrada = areaDeEstudoService.verificaAreasDeEstudo(temaTccDTO.getAreasDeEstudoRelacionadas());
		if (!(areaDeEstudoNaoCadastrada == null)) {
			return ErroTemaTcc.erroTemaComAreaNaoCadastrada(areaDeEstudoNaoCadastrada);
		}
		
		TemaTcc temaTcc = temaTccService.criarTemaTccAluno(temaTccDTO, aluno.getUsername());
		temaTccService.save(temaTcc);

		return new ResponseEntity<TemaTcc>(temaTcc, HttpStatus.OK);
	}

	@RequestMapping(value = "/aluno/professoresDisponiveis/{tokenAluno}", method = RequestMethod.GET)
	public ResponseEntity<?> listarProfessoresDisponiveis(@PathVariable("tokenAluno") long tokenAluno) {

		Optional<Aluno> alunoOp = alunoService.getById(tokenAluno);

		if (alunoOp.isEmpty()) {
			return ErroAluno.erroAlunoNaoEncontrado(tokenAluno);
		}

		Aluno aluno = alunoOp.get();

		List<ProfessorDisponivelDTO> listaProfessoresDisponiveis = professorService.listarProfessoresDisponiveis(aluno.getAreasDeEstudo());

		return new ResponseEntity<List<ProfessorDisponivelDTO>> (listaProfessoresDisponiveis, HttpStatus.OK);
	}

	@RequestMapping(value = "/aluno/temasTccProfessores/{tokenAluno}", method = RequestMethod.GET)
	public ResponseEntity<?> listarTemasTccProfessores(@PathVariable("tokenAluno") long tokenAluno) {

		Optional<Aluno> alunoOp = alunoService.getById(tokenAluno);

		if (alunoOp.isEmpty()) {
			return ErroAluno.erroAlunoNaoEncontrado(tokenAluno);
		}

		List<TemaTcc> listaTemasTcc = temaTccService.getTemasTccProfessores();

		return new ResponseEntity<List<TemaTcc>>(listaTemasTcc, HttpStatus.OK);
	}

}
