package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroSolicitacao {

	static final String SOLICITACAO_NAO_CADASTRADA = "A solicitação com id %s não está cadastrada";

	public static ResponseEntity<CustomErrorType> erroSolicitacaoNaoEncontrada(long id) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroSolicitacao.SOLICITACAO_NAO_CADASTRADA, id)), HttpStatus.NOT_FOUND);
	}

}
