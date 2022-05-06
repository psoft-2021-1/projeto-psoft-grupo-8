package com.ufcg.psoft.tccmatch.controller;

import com.ufcg.psoft.tccmatch.DTO.*;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.service.*;
import com.ufcg.psoft.tccmatch.util.ErroAluno;
import com.ufcg.psoft.tccmatch.util.ErroAreaDeEstudo;
import com.ufcg.psoft.tccmatch.util.ErroTemaTcc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

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
		String areaDeEstudoNaoCadastrada = areaDeEstudoService.verificaAreasDeEstudo(areasSelecionadasDTO.getAreasDeEstudo());
		
		if (!areaDeEstudoNaoCadastrada.isEmpty()) {
			return ErroAreaDeEstudo.erroAreaDeEstudoNaoCadastrada(areaDeEstudoNaoCadastrada);
		}
		
		Aluno aluno = alunoOp.get();
		List<AreaDeEstudo> areasDeEstudo = areaDeEstudoService.getAreasByNome(areasSelecionadasDTO.getAreasDeEstudo());
		aluno.setAreasDeEstudo(areasDeEstudo);
		alunoService.save(aluno);

		AlunoComAreasSelecionadasDTO alunoCadastrado = new AlunoComAreasSelecionadasDTO(aluno.getNome(),
				aluno.getUsername(), aluno.getEmail(), areasSelecionadasDTO);

		return new ResponseEntity<AlunoComAreasSelecionadasDTO>(alunoCadastrado, HttpStatus.OK);

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
		if (!areaDeEstudoNaoCadastrada.isEmpty()) {
			return ErroAreaDeEstudo.erroAreaDeEstudoNaoCadastrada(areaDeEstudoNaoCadastrada);
		}
		
		TemaTcc temaTcc = temaTccService.criarTemaTccAluno(temaTccDTO, aluno);
		temaTccService.save(temaTcc);
		TemaTccCadastradoDTO temaTccCadastradoDTO = temaTccService.criarTemaTccCadastradoDTO(temaTcc);

		return new ResponseEntity<TemaTccCadastradoDTO>(temaTccCadastradoDTO, HttpStatus.OK);
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
		List<TemaTccCadastradoDTO> listaTemasTccDTO = temaTccService.getTemasTccDTO(listaTemasTcc);

		return new ResponseEntity<List<TemaTccCadastradoDTO>>(listaTemasTccDTO, HttpStatus.OK);
	}

}
