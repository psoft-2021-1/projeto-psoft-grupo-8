package com.ufcg.psoft.tccmatch.controller;

import com.ufcg.psoft.tccmatch.DTO.AlunoDTO;
import com.ufcg.psoft.tccmatch.DTO.OrientacaoDTO;
import com.ufcg.psoft.tccmatch.model.*;
import com.ufcg.psoft.tccmatch.service.*;
import com.ufcg.psoft.tccmatch.util.ErroAluno;
import com.ufcg.psoft.tccmatch.util.ErroCoordenador;
import com.ufcg.psoft.tccmatch.util.ErroProfessor;
import com.ufcg.psoft.tccmatch.util.ErroTemaTcc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class OrientacaoController {

	@Autowired
	CoordenadorService coordenadorService;

	@Autowired
	ProfessorService professorService;

	@Autowired
	AlunoService alunoService;

	@Autowired
	OrientacaoService orientacaoService;

	@Autowired
	TemaTccService temaTccService;

	@RequestMapping(value = "/orientacao/{tokenCoordenador}", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarOrientacao(@RequestBody OrientacaoDTO orientacaoDTO, UriComponentsBuilder ucBuilder,
                                                 @PathVariable("tokenCoordenador") long idCoordenador) {

        Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);

        if (coordenadorOp.isEmpty()) {
            return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
        }

        Optional<Aluno> alunoOp = alunoService.findByUsername(orientacaoDTO.getMatriculaAluno().toString());

        if (alunoOp.isEmpty()) {
            return ErroAluno.erroAlunoNaoEncontradoMatricula(orientacaoDTO.getMatriculaAluno());
        }
        Aluno aluno = alunoOp.get();

        Optional<Professor> professorOp = professorService.findByUsername(orientacaoDTO.getCpfProfessor().toString());

        if (professorOp.isEmpty()) {
            return ErroProfessor.erroProfessorNaoEncontradoCpf(orientacaoDTO.getCpfProfessor());
        }
        Professor professor = professorOp.get();

        Optional<TemaTcc> temaTccOp = temaTccService.getByTitulo(orientacaoDTO.getTituloTema());

        if (temaTccOp.isEmpty()) {
            return ErroTemaTcc.erroTemaNaoCadastrado(orientacaoDTO.getTituloTema());
        }
        TemaTcc temaTcc = temaTccOp.get();

        if (!temaTcc.getUsernameCriador().equals(professor.getUsername()) &&
            !temaTcc.getUsernameCriador().equals(aluno.getUsername())) {
            return ErroTemaTcc.erroTemaCriadorAusente(orientacaoDTO.getTituloTema());
        }

        Orientacao orientacao = orientacaoService.cadastrarOrientacao(aluno, temaTcc, professor, orientacaoDTO.getSemestre());
        orientacaoService.save(orientacao);

        return new ResponseEntity<Orientacao>(orientacao, HttpStatus.OK);
    }

}
