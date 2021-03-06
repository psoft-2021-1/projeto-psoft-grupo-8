package com.ufcg.psoft.tccmatch.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ReturnMessage {
	
	static final String SOLICITACAO_ENVIADA = "A solicitação foi enviada para o e-mail do destinatário";
	
	static final String DECISAO_SOLICITACAO = "A decisão %s da solicitação %s foi registrada com sucesso";
	
	static final String CADASTRO_PROBLEMA = "O usuário do tipo %s com nome %s cadastrou um problema com sucesso na Orientação de id %s";

	static final String ALUNO_REMOVIDO = "O aluno foi removido com sucesso";

	static final String PROFESSOR_REMOVIDO = "O professor foi removido com sucesso";
	
	public static ResponseEntity<CustomReturnMessage> solicitacaoEnviada() {
		return new ResponseEntity<CustomReturnMessage>(new CustomReturnMessage(String.format(ReturnMessage.SOLICITACAO_ENVIADA)),
				HttpStatus.OK);
	}
	
	public static ResponseEntity<CustomReturnMessage> decisaoSolicitacao(boolean decisao, long id) {
		return new ResponseEntity<CustomReturnMessage>(new CustomReturnMessage(String.format(ReturnMessage.DECISAO_SOLICITACAO, decisao, id)),
				HttpStatus.OK);
	}
	
	public static ResponseEntity<CustomReturnMessage> cadastroProblema(String tipoUsuario, String nome, Long id) {
		return new ResponseEntity<CustomReturnMessage>(new CustomReturnMessage(String.format(ReturnMessage.CADASTRO_PROBLEMA, tipoUsuario, nome, id)),
				HttpStatus.OK);
	}

	public static ResponseEntity<CustomReturnMessage> removeProfessor() {
		return new ResponseEntity<CustomReturnMessage>(new CustomReturnMessage(String.format(ReturnMessage.PROFESSOR_REMOVIDO)),
				HttpStatus.OK);
	}

	public static ResponseEntity<CustomReturnMessage> removeAluno() {
		return new ResponseEntity<CustomReturnMessage>(new CustomReturnMessage(String.format(ReturnMessage.ALUNO_REMOVIDO)),
				HttpStatus.OK);
	}

}
