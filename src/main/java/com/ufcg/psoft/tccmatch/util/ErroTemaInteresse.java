package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroTemaInteresse {
	
	static final String TEMA_INTERESSE_NAO_CADASTRADO = "O tema interesse com id %s não está cadastrada";

	public static ResponseEntity<CustomErrorType> erroTemaInteresseNaoEncontrado(long id) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroTemaInteresse.TEMA_INTERESSE_NAO_CADASTRADO, id)), HttpStatus.NOT_FOUND);
	}

}
