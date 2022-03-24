package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroTemaTcc {
	static final String TEMA_JA_CADASTRADO = "TCC de tema com título %s já está cadastrado";

	public static ResponseEntity<CustomErrorType> erroTemaJaCadastrado(String nome) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroTemaTcc.TEMA_JA_CADASTRADO, nome)), HttpStatus.CONFLICT);
	}
}
