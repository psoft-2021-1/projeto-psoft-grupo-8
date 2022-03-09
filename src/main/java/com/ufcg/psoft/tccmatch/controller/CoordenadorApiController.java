package com.ufcg.psoft.tccmatch.controller;

import com.ufcg.psoft.tccmatch.DTO.AlunoDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Coordenador;
import com.ufcg.psoft.tccmatch.service.AlunoService;
import com.ufcg.psoft.tccmatch.service.CoordenadorService;
import com.ufcg.psoft.tccmatch.util.ErroAluno;
import com.ufcg.psoft.tccmatch.util.ErroCoordenador;

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

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CoordenadorApiController {
	
	@Autowired
	CoordenadorService coordenadorService;
	
    @Autowired
    AlunoService alunoService;

    @RequestMapping(value = "/cadastrar/aluno/{idCoordenador}", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarAluno(@RequestBody AlunoDTO alunoDTO, UriComponentsBuilder ucBuilder,
    										@PathVariable("idCoordenador") long idCoordenador) {
    	
    	Optional<Coordenador> coordenadorOp = coordenadorService.getCoordenadorById(idCoordenador);
    	
    	if (coordenadorOp.isEmpty()) {
    		return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
    	}  	
    	
    	Optional<Aluno> alunoOp = alunoService.getAlunoByMatricula(alunoDTO.getMatricula());
    	
    	if (!alunoOp.isEmpty()) {
    		return ErroAluno.erroAlunoJaCadastrado(alunoDTO.getMatricula());
    	}
    	
    	Aluno aluno = alunoService.criarAluno(alunoDTO);
    	alunoService.salvarAluno(aluno);
    	
        return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
    }

}
