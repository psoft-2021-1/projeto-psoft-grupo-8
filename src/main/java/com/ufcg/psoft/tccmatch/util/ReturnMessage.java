package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ReturnMessage {
	
	static final String SOLICITACAO_ENVIADA = "A solicitação foi enviada para o e-mail do destinatário";
	
	static final String DECISAO_SOLICITACAO = "A decisão %s da solicitação %s foi registrada com sucesso";
	
	public static ResponseEntity<CustomReturnMessage> solicitacaoEnviada() {
		return new ResponseEntity<CustomReturnMessage>(new CustomReturnMessage(String.format(ReturnMessage.SOLICITACAO_ENVIADA)),
				HttpStatus.OK);
	}
	
	public static ResponseEntity<CustomReturnMessage> decisaoSolicitacao(boolean decisao, long id) {
		return new ResponseEntity<CustomReturnMessage>(new CustomReturnMessage(String.format(ReturnMessage.DECISAO_SOLICITACAO, decisao, id)),
				HttpStatus.OK);
	}

}
