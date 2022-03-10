package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroProfessor {
	static final String PROFESSOR_NAO_CADASTRADO = "Professor com id %s não está cadastrado";
	
	static final String PROFESSOR_JA_CADASTRADO = "O professor com CPF %s já está cadastrado";
	
	public static ResponseEntity<CustomErrorType> erroProfessorNaoEncontrado(long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroProfessor.PROFESSOR_NAO_CADASTRADO, id)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroProfessorJaCadastrado(long matricula) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroProfessor.PROFESSOR_JA_CADASTRADO, matricula)),
				HttpStatus.CONFLICT);
	}
}