package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroAluno {	

	static final String ALUNO_NAO_CADASTRADO = "Aluno com id %s não está cadastrado";
	
	static final String ALUNO_JA_CADASTRADO = "O aluno com matrícula %s já esta cadastrado";
	
	public static ResponseEntity<CustomErrorType> erroAlunoNaoEncontrado(long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAluno.ALUNO_NAO_CADASTRADO, id)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroAlunoJaCadastrado(long matricula) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAluno.ALUNO_JA_CADASTRADO, matricula)),
				HttpStatus.CONFLICT);
	}
	
}
