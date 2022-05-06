package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroCoordenador {

	static final String COORDENADOR_NAO_CADASTRADO = "Coordenador com id %s não está cadastrado";
	
	public static ResponseEntity<CustomErrorType> erroCoordenadorNaoCadastrado(long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCoordenador.COORDENADOR_NAO_CADASTRADO, id)),
				HttpStatus.NOT_FOUND);
	}
	
}
