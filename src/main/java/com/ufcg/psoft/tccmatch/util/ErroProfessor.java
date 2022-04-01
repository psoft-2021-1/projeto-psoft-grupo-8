package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroProfessor {
	static final String PROFESSOR_NAO_CADASTRADO = "Professor com id %s não está cadastrado";
	
	static final String PROFESSOR_NAO_CADASTRADO_CPF = "O professor com CPF %s não está cadastrado";
	
	static final String PROFESSOR_JA_CADASTRADO = "O professor com CPF %s já está cadastrado";
	
	static final String PROFESSOR_QUOTA_INSUFICIENTE = "O professor com id %s não tem quota suficiente";

	
	public static ResponseEntity<CustomErrorType> erroProfessorNaoEncontrado(long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroProfessor.PROFESSOR_NAO_CADASTRADO, id)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<?> erroProfessorNaoEncontradoCpf(long cpf) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroProfessor.PROFESSOR_NAO_CADASTRADO_CPF, cpf)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroProfessorJaCadastrado(long cpf) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroProfessor.PROFESSOR_JA_CADASTRADO, cpf)),
				HttpStatus.CONFLICT);
	}

	public static ResponseEntity<?> erroProfessorQuotaInsuficiente(long idProfessor) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroProfessor.PROFESSOR_QUOTA_INSUFICIENTE, idProfessor)),
				HttpStatus.NOT_ACCEPTABLE);
	}


}
