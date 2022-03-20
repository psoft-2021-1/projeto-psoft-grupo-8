package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroAreaDeEstudo {
	
	static final String AREA_JA_CADASTRADA = "Área de estudo com nome %s já está cadastrada";
	
	public static ResponseEntity<CustomErrorType> erroAreaDeEstudoJaCadastrada(String nome) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAreaDeEstudo.AREA_JA_CADASTRADA, nome)),
				HttpStatus.CONFLICT);
	}
}
