package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroOrientacao {
	
	static final String ALUNO_JA_TEM_ORIENTACAO = "Aluno com nome %s já está participando de uma orientação cadastrada";
	
	static final String ORIENTACAO_NAO_EXISTE = "Orientação com id %s não existe";
	
	static final String ORIENTACAO_JA_TEM_PROBLEMA = "Orientação com id %s não existe";

	public static ResponseEntity<CustomErrorType> alunoJaTemOrientacao(String nomeAluno) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroOrientacao.ALUNO_JA_TEM_ORIENTACAO, nomeAluno)),
				HttpStatus.CONFLICT);
	}
	
	public static ResponseEntity<CustomErrorType> orientacaoNaoExiste(Long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroOrientacao.ORIENTACAO_NAO_EXISTE, id)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> orientacaoJaTemProblema(Long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroOrientacao.ORIENTACAO_JA_TEM_PROBLEMA, id)),
				HttpStatus.NOT_FOUND);
	}
}
