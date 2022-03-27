package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroTemaTcc {

	static final String TEMA_JA_CADASTRADO = "Tema de TCC com título %s já está cadastrado";
  
	static final String TEMA_NAO_CADASTRADO = "Tema de TCC com título %s não está cadastrado";

	static final String TEMA_NAO_ALUNO = "Tema de TCC com título %s não foi cadastrado por um aluno";
	
	static final String TEMA_NAO_PROFESSOR = "Tema de TCC com título %s não foi cadastrado por um professor";

	static final String TEMA_CRIADOR_AUSENTE = "O tema de tcc com título %s não reconhece o criador";

	public static ResponseEntity<CustomErrorType> erroTemaJaCadastrado(String titulo) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroTemaTcc.TEMA_JA_CADASTRADO, titulo.toUpperCase())), HttpStatus.CONFLICT);
	}
  
    public static ResponseEntity<?> erroTemaNaoCadastrado(String titulo) {
    	return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroTemaTcc.TEMA_NAO_CADASTRADO, titulo.toUpperCase())), HttpStatus.NOT_FOUND);
    }

	public static ResponseEntity<?> erroTemaNaoAluno(String titulo) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroTemaTcc.TEMA_NAO_ALUNO, titulo.toUpperCase())), HttpStatus.BAD_REQUEST);
	}
	
	public static ResponseEntity<?> erroTemaNaoProfessor(String titulo) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroTemaTcc.TEMA_NAO_PROFESSOR, titulo.toUpperCase())), HttpStatus.BAD_REQUEST);
	}

    public static ResponseEntity<?> erroTemaCriadorAusente(String titulo) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroTemaTcc.TEMA_CRIADOR_AUSENTE, titulo.toUpperCase())), HttpStatus.NOT_ACCEPTABLE);
	}
}
