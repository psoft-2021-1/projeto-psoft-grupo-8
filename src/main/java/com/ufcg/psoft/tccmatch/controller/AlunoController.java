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
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.service.AlunoService;
import com.ufcg.psoft.tccmatch.service.AreaDeEstudoService;
import com.ufcg.psoft.tccmatch.util.ErroAluno;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class AlunoController {
	
	@Autowired
	AlunoService alunoService;
	
	@Autowired
    AreaDeEstudoService areaDeEstudoService;
	
    @RequestMapping(value = "/aluno/areaDeEstudo/{tokenAluno}", method = RequestMethod.POST)
    public ResponseEntity<?> selecionarAreasDeEstudo(@RequestBody AreasSelecionadasDTO areasSelecionadasDTO, UriComponentsBuilder ucBuilder,
    											  @PathVariable("tokenAluno") long idAluno) {
    	
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
}
