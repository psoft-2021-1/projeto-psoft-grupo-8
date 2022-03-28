package com.ufcg.psoft.tccmatch.controller;

import com.ufcg.psoft.tccmatch.DTO.AlunoCadastradoDTO;
import com.ufcg.psoft.tccmatch.DTO.AlunoDTO;
import com.ufcg.psoft.tccmatch.DTO.ProfessorCadastradoDTO;
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
import com.ufcg.psoft.tccmatch.util.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adm/")
@CrossOrigin
public class CoordenadorApiController {
	
	@Autowired
	CoordenadorService coordenadorService;
	
    @Autowired
    AlunoService alunoService;
    
    @Autowired
    ProfessorService professorService;

    @RequestMapping(value = "/aluno/{tokenCoordenador}", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarAluno(@RequestBody AlunoDTO alunoDTO, UriComponentsBuilder ucBuilder,
    										@PathVariable("tokenCoordenador") long idCoordenador) {
    	
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

		AlunoCadastradoDTO alunoCadastradoDTO = new AlunoCadastradoDTO(aluno.getNome(), aluno.getUsername(),
				aluno.getPeriodoConclusao(), aluno.getEmail());

		return new ResponseEntity<AlunoCadastradoDTO>(alunoCadastradoDTO, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/aluno/{tokenCoordenador}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizarAluno(@RequestBody AlunoDTO alunoDTO, UriComponentsBuilder ucBuilder,
											@PathVariable("tokenCoordenador") long idCoordenador) {
    	
    	Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);
    	
    	if (coordenadorOp.isEmpty()) {
    		return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
    	}
    	
    	Optional<Aluno> alunoOp = alunoService.findByUsername(alunoDTO.getMatricula().toString());
		
		if (alunoOp.isEmpty()) {
			return ErroAluno.erroAlunoNaoEncontradoMatricula(alunoDTO.getMatricula());
		}
		
		Aluno aluno = alunoOp.get();
    	
    	alunoService.atualizarAluno(alunoDTO, aluno);
    	alunoService.save(aluno);

		AlunoCadastradoDTO alunoCadastradoDTO = new AlunoCadastradoDTO(aluno.getNome(), aluno.getUsername(),
				aluno.getPeriodoConclusao(), aluno.getEmail());

		return new ResponseEntity<AlunoCadastradoDTO>(alunoCadastradoDTO, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/aluno/{tokenCoordenador}/{matricula}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removerAluno(@PathVariable("tokenCoordenador") long idCoordenador,
    									  @PathVariable("matricula") Long matriculaAluno) {
    	
    	Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);
    	
    	if (coordenadorOp.isEmpty()) {
    		return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
    	}
    	
		Optional<Aluno> alunoOp = alunoService.findByUsername(matriculaAluno.toString());
		
		if (!alunoOp.isPresent()) {
			return ErroAluno.erroAlunoNaoEncontradoMatricula(matriculaAluno);
		}
		
		alunoService.removerAluno(alunoOp.get());

		return ReturnMessage.removeAluno();
    }
    
    @RequestMapping(value = "/professor/{tokenCoordenador}", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarProfessor(@RequestBody ProfessorDTO professorDTO, UriComponentsBuilder ucBuilder,
    										@PathVariable("tokenCoordenador") long idCoordenador) {
    	
    	Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);
    	
    	if (coordenadorOp.isEmpty()) {
    		return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
    	}  	
    	
    	Optional<Professor> professorOp = professorService.findByUsername(professorDTO.getCPF().toString());
    	
    	if (!professorOp.isEmpty()) {
    		return ErroProfessor.erroProfessorJaCadastrado(professorDTO.getCPF());
    	}
    	
    	Professor professor = professorService.criarProfessor(professorDTO);
    	professorService.save(professor);

		ProfessorCadastradoDTO professorCadastradoDTO = new ProfessorCadastradoDTO(professor.getNome(),
				professor.getUsername(), professor.getEmail(), professor.getQuota(), professor.getLaboratorios());

		return new ResponseEntity<ProfessorCadastradoDTO>(professorCadastradoDTO, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/professores", method = RequestMethod.GET)
    public ResponseEntity<?> listarProfessores() {
		List<Professor> listaProfessores = professorService.findAll();
		List<ProfessorCadastradoDTO> professores = new ArrayList<ProfessorCadastradoDTO>();

		for (Professor professor : listaProfessores) {
			ProfessorCadastradoDTO professorCadastradoDTO = new ProfessorCadastradoDTO(professor.getNome(),
					professor.getUsername(), professor.getEmail(), professor.getQuota(), professor.getLaboratorios());
			professores.add(professorCadastradoDTO);
		}

		return new ResponseEntity<List<ProfessorCadastradoDTO>>(professores, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/professor/{tokenCoordenador}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizarProfessor(@RequestBody ProfessorDTO professorDTO, UriComponentsBuilder ucBuilder,
			@PathVariable("tokenCoordenador") long idCoordenador) {
    	
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

		ProfessorCadastradoDTO professorCadastradoDTO = new ProfessorCadastradoDTO(professor.getNome(),
				professor.getUsername(), professor.getEmail(), professor.getQuota(), professor.getLaboratorios());

		return new ResponseEntity<ProfessorCadastradoDTO>(professorCadastradoDTO, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/professor/{tokenCoordenador}/{cpf}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removerProfessor(@PathVariable("tokenCoordenador") long idCoordenador, 
    										  @PathVariable("cpf") Long cpfProfessor) {
    	
    	Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);
    	
    	if (coordenadorOp.isEmpty()) {
    		return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
    	}
    	
		Optional<Professor> professorOp = professorService.findByUsername(cpfProfessor.toString());
		
		if (!professorOp.isPresent()) {
			return ErroProfessor.erroProfessorNaoEncontradoCpf(cpfProfessor);
		}
		
		professorService.removerProfessor(professorOp.get());

		return ReturnMessage.removeProfessor();
    }
}
