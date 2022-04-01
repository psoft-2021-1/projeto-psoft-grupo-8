package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroAreaDeEstudo {
	
	static final String AREA_JA_CADASTRADA = "Área de estudo com nome %s já está cadastrada";
	
	static final String AREA_NAO_CADASTRADA = "Área de estudo com nome %s não está cadastrada";
	
	static final String AREAS_NAO_CADASTRADAS = "Não há áreas de estudo cadastradas";
	
	public static ResponseEntity<CustomErrorType> erroAreaDeEstudoJaCadastrada(String nome) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAreaDeEstudo.AREA_JA_CADASTRADA, nome)),
				HttpStatus.CONFLICT);
	}
	
	public static ResponseEntity<CustomErrorType> erroAreaDeEstudoNaoCadastrada(String nome) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAreaDeEstudo.AREA_NAO_CADASTRADA, nome)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroAreasDeEstudoNaoCadastradas() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroAreaDeEstudo.AREAS_NAO_CADASTRADAS)),
				HttpStatus.NO_CONTENT);
	}
}
