package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroLogin {
	
	static final String SERVICE_INDISPONIVEL = "Service com nome %s não está disponível";
	
	static final String LOGIN_INCORRETO = "Usuário ou senha incorretos";
	
	static final String TOKEN_INVALIDO = "Token %s inválido";
	
	public static ResponseEntity<CustomErrorType> erroServiceIndisponivel(String tipoService) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroLogin.SERVICE_INDISPONIVEL, tipoService)),
				HttpStatus.NOT_IMPLEMENTED);
	}
	
	public static ResponseEntity<CustomErrorType> erroUsernameSenhaIncorretos() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroLogin.LOGIN_INCORRETO)),
				HttpStatus.BAD_REQUEST);  //checar se tipo de erro está correto
	}
	
	public static ResponseEntity<CustomErrorType> erroTokenInvalido(long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroLogin.TOKEN_INVALIDO, id)),
				HttpStatus.BAD_REQUEST);
	}

}
