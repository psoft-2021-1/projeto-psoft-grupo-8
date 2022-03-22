package com.ufcg.psoft.tccmatch.controller;

import java.util.List;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.psoft.tccmatch.DTO.AreasSelecionadasDTO;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.service.AreaDeEstudoService;
import com.ufcg.psoft.tccmatch.service.ProfessorService;
import com.ufcg.psoft.tccmatch.util.ErroProfessor;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class ProfessorController {
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
    AreaDeEstudoService areaDeEstudoService;
	
	@RequestMapping(value = "/professor/areaDeEstudo/{tokenProfessor}", method = RequestMethod.POST)
    public ResponseEntity<?> selecionarAreasDeEstudo(@RequestBody AreasSelecionadasDTO areasSelecionadasDTO, UriComponentsBuilder ucBuilder,
    											  @PathVariable("tokenProfessor") long idProfessor) {
    	
    	Optional<Professor> professorOp = professorService.getById(idProfessor);
    	
    	if (professorOp.isEmpty()) {
    		return ErroProfessor.erroProfessorNaoEncontrado(idProfessor);
    	}  	
    	
    	Professor professor = professorOp.get();
    	List<AreaDeEstudo> areasDeEstudo = areaDeEstudoService.getAreasByNome(areasSelecionadasDTO.getAreasDeEstudos());
    	
    	professor.setAreasDeEstudo(areasDeEstudo);
    	professorService.save(professor);   	

    	return new ResponseEntity<Professor>(professor, HttpStatus.OK);
    }
}