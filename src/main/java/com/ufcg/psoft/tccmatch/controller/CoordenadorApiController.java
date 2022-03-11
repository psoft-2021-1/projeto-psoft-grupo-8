package com.ufcg.psoft.tccmatch.controller;

import com.ufcg.psoft.tccmatch.DTO.AlunoDTO;
import com.ufcg.psoft.tccmatch.DTO.ProfessorDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Coordenador;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.service.AlunoService;
import com.ufcg.psoft.tccmatch.service.CoordenadorService;
import com.ufcg.psoft.tccmatch.service.ProfessorService;
import com.ufcg.psoft.tccmatch.util.ErroAluno;
import com.ufcg.psoft.tccmatch.util.ErroCoordenador;
import com.ufcg.psoft.tccmatch.util.ErroProfessor;

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

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CoordenadorApiController {
	
	@Autowired
	CoordenadorService coordenadorService;
	
    @Autowired
    AlunoService alunoService;
    
    @Autowired
    ProfessorService professorService;

    @RequestMapping(value = "/cadastrar/aluno/{idCoordenador}", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarAluno(@RequestBody AlunoDTO alunoDTO, UriComponentsBuilder ucBuilder,
    										@PathVariable("idCoordenador") long idCoordenador) {
    	
    	Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);
    	
    	if (coordenadorOp.isEmpty()) {
    		return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
    	}  	
    	
    	Optional<Aluno> alunoOp = alunoService.findByUsername(alunoDTO.getMatricula().toString());
    	
    	if (!alunoOp.isEmpty()) {
    		return ErroAluno.erroAlunoJaCadastrado(alunoDTO.getMatricula());
    	}
    	
    	Aluno aluno = alunoService.criarAluno(alunoDTO);
    	alunoService.save(aluno);
    	
        return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/cadastrar/professor/{idCoordenador}", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarProfessor(@RequestBody ProfessorDTO professorDTO, UriComponentsBuilder ucBuilder,
    										@PathVariable("idCoordenador") long idCoordenador) {
    	
    	Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);
    	
    	if (coordenadorOp.isEmpty()) {
    		return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
    	}  	
    	
    	Optional<Professor> professorOP = professorService.findByUsername(professorDTO.getCPF().toString());
    	
    	if (!professorOP.isEmpty()) {
    		return ErroProfessor.erroProfessorJaCadastrado(professorDTO.getCPF());
    	}
    	
    	Professor professor = professorService.criarProfessor(professorDTO);
    	professorService.save(professor);
    	
        return new ResponseEntity<Professor>(professor, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/listar/professores", method = RequestMethod.GET)
    public ResponseEntity<?> listarProfessores() {
    	List<Professor> listaProfessores = professorService.findAll();
    	String professores = "";
    	
    	for (Professor professor: listaProfessores) {
    		professores += professor.getNome() + "\n";
    	}
    	  	
        return new ResponseEntity<String>(professores, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/atualizar/professor/{idCoordenador}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizarProfessor(@RequestBody ProfessorDTO professorDTO, UriComponentsBuilder ucBuilder,
			@PathVariable("idCoordenador") long idCoordenador) {
    	
    	Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);
    	
    	if (coordenadorOp.isEmpty()) {
    		return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
    	}
    	
		Optional<Professor> professorOp = professorService.findByUsername(professorDTO.getCPF().toString());
		
		if (!professorOp.isPresent()) {
			return ErroProfessor.erroProfessorNaoEncontradoCpf(professorDTO.getCPF());
		}
		
		Professor professor = professorOp.get();
    	
    	professorService.atualizarProfessor(professorDTO, professor);
    	professorService.save(professor);
    	
        return new ResponseEntity<Professor>(professor, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/remover/professor/{idCoordenador}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removerProfessor(@PathVariable("idCoordenador") long idCoordenador, @RequestBody Long cpf) {
    	
    	Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);
    	
    	if (coordenadorOp.isEmpty()) {
    		return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
    	}
    	
		Optional<Professor> professorOp = professorService.findByUsername(cpf.toString());
		
		if (!professorOp.isPresent()) {
			return ErroProfessor.erroProfessorNaoEncontradoCpf(cpf);
		}
		
		professorService.removerProfessor(professorOp.get());
    	
        return new ResponseEntity<Professor>(HttpStatus.OK);
    }
}
