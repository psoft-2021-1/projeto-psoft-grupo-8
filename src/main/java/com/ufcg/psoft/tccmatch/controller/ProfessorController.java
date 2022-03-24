package com.ufcg.psoft.tccmatch.controller;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.util.ErroAluno;
import com.ufcg.psoft.tccmatch.util.ErroAreaDeEstudo;

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
import com.ufcg.psoft.tccmatch.DTO.TemaTccProfessorDTO;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.service.AreaDeEstudoService;
import com.ufcg.psoft.tccmatch.service.ProfessorService;
import com.ufcg.psoft.tccmatch.service.TemaTccService;
import com.ufcg.psoft.tccmatch.util.ErroProfessor;
import com.ufcg.psoft.tccmatch.util.ErroTemaTcc;

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
	
	@RequestMapping(value = "/professor/areaDeEstudo/{tokenProfessor}", method = RequestMethod.POST)
    public ResponseEntity<?> selecionarAreasDeEstudo(@RequestBody AreasSelecionadasDTO areasSelecionadasDTO, UriComponentsBuilder ucBuilder,
    											  @PathVariable("tokenProfessor") long idProfessor) {
    	
    	Optional<Professor> professorOp = professorService.getById(idProfessor);
    	
    	if (professorOp.isEmpty()) {
    		return ErroProfessor.erroProfessorNaoEncontrado(idProfessor);
    	}  	
    	
    	String areaDeEstudoNaoCadastrada = areaDeEstudoService.verificaAreasDeEstudo(areasSelecionadasDTO.getAreasDeEstudo());
    	
		if (!areaDeEstudoNaoCadastrada.isEmpty()) {
			return ErroAreaDeEstudo.erroAreaDeEstudoNaoCadastrada(areaDeEstudoNaoCadastrada);
		}
    	
    	Professor professor = professorOp.get();
    	List<AreaDeEstudo> areasDeEstudo = areaDeEstudoService.getAreasByNome(areasSelecionadasDTO.getAreasDeEstudo());
    	
    	professor.setAreasDeEstudo(areasDeEstudo);
    	professorService.save(professor);   	

    	return new ResponseEntity<Professor>(professor, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/professor/temaTCC/{tokenProfessor}", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarTemaTcc(@RequestBody TemaTccProfessorDTO temaTccDTO, UriComponentsBuilder ucBuilder,
											  @PathVariable("tokenProfessor") long idProfessor) {

		Optional<Professor> professorOp = professorService.getById(idProfessor);

		if (professorOp.isEmpty()) {
			return ErroProfessor.erroProfessorNaoEncontrado(idProfessor);
		}

		Optional<TemaTcc> temaTccOp = temaTccService.getByTitulo(temaTccDTO.getTitulo());

		if (!temaTccOp.isEmpty()) {
			return ErroTemaTcc.erroTemaJaCadastrado(temaTccDTO.getTitulo());
		}

		Professor professor = professorOp.get();
		
		String checagemAreaEstudo = areaDeEstudoService.verificaAreasDeEstudo(temaTccDTO.getAreasDeEstudoRelacionadas());
		if (!checagemAreaEstudo.isEmpty()) {
			return ErroTemaTcc.erroTemaComAreaNaoCadastrada(checagemAreaEstudo);
		}
		
		TemaTcc temaTcc = temaTccService.criarTemaTccProfessor(temaTccDTO, professor.getUsername());
		temaTccService.save(temaTcc);

		return new ResponseEntity<TemaTcc>(temaTcc, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/professor/quota/{tokenProfessor}", method = RequestMethod.POST)
    public ResponseEntity<?> configurarQuota(@RequestBody Integer quota, @PathVariable("tokenProfessor") long idProfessor) {
    	
    	Optional<Professor> professorOp = professorService.getById(idProfessor);
    	
    	if (professorOp.isEmpty()) {
    		return ErroProfessor.erroProfessorNaoEncontrado(idProfessor);
    	}  	
    	
    	Professor professor = professorOp.get();
    	
    	professor.setQuota(quota);
		professorService.save(professor);
    	
    	return new ResponseEntity<Professor>(professor, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/professor/temasTccCadastrei/{tokenProfessor}", method = RequestMethod.GET)
	public ResponseEntity<?> listarTemasTccCadastrei(@PathVariable("tokenProfessor") long idProfessor) {

		Optional<Professor> professorOp = professorService.getById(idProfessor);

		if (professorOp.isEmpty()) {
			return ErroProfessor.erroProfessorNaoEncontrado(idProfessor);
		}

		List<TemaTcc> listaTemasTcc = temaTccService.getTemasTccProfessor(professorOp.get().getUsername());

		return new ResponseEntity<List<TemaTcc>>(listaTemasTcc, HttpStatus.OK);
	}

	@RequestMapping(value = "/aluno/temasTccAlunos/{tokenProfessor}", method = RequestMethod.GET)
	public ResponseEntity<?> listarTemasTccAlunos(@PathVariable("tokenProfessor") long tokenProfessor) {

		Optional<Professor> professorOp = professorService.getById(tokenProfessor);

		if (professorOp.isEmpty()) {
			return ErroProfessor.erroProfessorNaoEncontrado(tokenProfessor);
		}

		List<TemaTcc> listaTemasTcc = temaTccService.getTemasTccAlunos();

		return new ResponseEntity<List<TemaTcc>>(listaTemasTcc, HttpStatus.OK);
	}
}
