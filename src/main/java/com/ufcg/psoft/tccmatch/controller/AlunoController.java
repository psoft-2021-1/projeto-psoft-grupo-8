package com.ufcg.psoft.tccmatch.controller;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.TemaTccDTOAluno;
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
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.service.AlunoService;
import com.ufcg.psoft.tccmatch.service.AreaDeEstudoService;
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

	@RequestMapping(value = "/aluno/areaDeEstudo/{tokenAluno}", method = RequestMethod.POST)
	public ResponseEntity<?> selecionarAreasDeEstudo(@RequestBody AreasSelecionadasDTO areasSelecionadasDTO,
			UriComponentsBuilder ucBuilder, @PathVariable("tokenAluno") long idAluno) {

		Optional<Aluno> alunoOp = alunoService.getById(idAluno);

		if (alunoOp.isEmpty()) {
			return ErroAluno.erroAlunoNaoEncontrado(idAluno);
		}

		Aluno aluno = alunoOp.get();
		List<AreaDeEstudo> areasDeEstudo = areaDeEstudoService.getAreasByNome(areasSelecionadasDTO.getAreasDeEstudos());

		aluno.setAreasDeEstudo(areasDeEstudo);
		alunoService.save(aluno);

		return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
	}

	@RequestMapping(value = "/aluno/temaTCC/{tokenAluno}", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarTemaTcc(@RequestBody TemaTccDTOAluno temaTccDTO, UriComponentsBuilder ucBuilder,
											  @PathVariable("tokenAluno") long idAluno) {

		Optional<Aluno> alunoOp = alunoService.getById(idAluno);

		if (alunoOp.isEmpty()) {
			return ErroAluno.erroAlunoNaoEncontrado(idAluno);
		}

		Optional<TemaTcc> temaTccOp = temaTccService.getByTitulo(temaTccDTO.getTitulo());

		if (!temaTccOp.isEmpty()) {
			return ErroTemaTcc.erroTemaJaCadastrado(temaTccDTO.getTitulo());
		}

		List<AreaDeEstudo> areasDeEstudo = temaTccDTO.getAreaDeEstudoRelacionadas();
		for (AreaDeEstudo areaDeEstudo : areasDeEstudo) {
			if (areaDeEstudoService.getDiretamenteByNome(areaDeEstudo.getNome()) == null) {
				return ErroTemaTcc.erroTemaComAreaNaoCadastrada(areaDeEstudo.getNome());
			}
		}
		TemaTcc temaTcc = temaTccService.criarTemaTccAluno(temaTccDTO);
		temaTccService.save(temaTcc);

		return new ResponseEntity<TemaTcc>(temaTcc, HttpStatus.OK);
	}

	@RequestMapping(value = "/aluno/temasTcc", method = RequestMethod.GET)
	public ResponseEntity<?> listarTemasTccCadastrados() {
		List<TemaTcc> listaTemasTcc = temaTccService.getTemasTcc();
		String temas = "";

		for (TemaTcc tema : listaTemasTcc) {
			temas += tema.getTitulo() + "\n";
		}

		return new ResponseEntity<String>(temas, HttpStatus.OK);
	}

	@RequestMapping(value = "/aluno/professoresDisponiveis", method = RequestMethod.GET)
	public ResponseEntity<?> listarProfessoresDisponiveis(@PathVariable("tokenAluno") long idAluno) {

		Optional<Aluno> alunoOp = alunoService.getById(idAluno);

		if (alunoOp.isEmpty()) {
			return ErroAluno.erroAlunoNaoEncontrado(idAluno);
		}

		Aluno aluno = alunoOp.get();

		List<Professor> listaProfessoresDisponiveis = professorService.listarProfessoresDisponiveis(aluno.getAreasDeEstudo());

		return new ResponseEntity<List<Professor>> (listaProfessoresDisponiveis, HttpStatus.OK);
	}

}
