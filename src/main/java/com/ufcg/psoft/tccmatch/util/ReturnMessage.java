package com.ufcg.psoft.tccmatch.util;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufcg.psoft.tccmatch.DTO.RelatorioProblemaIndividualDTO;

public class ReturnMessage {
	
	static final String SOLICITACAO_ENVIADA = "A solicitação foi enviada para o e-mail do destinatário";
	
	static final String DECISAO_SOLICITACAO = "A decisão %s da solicitação %s foi registrada com sucesso";
	
	static final String CADASTRO_PROBLEMA = "O usuário do tipo %s com nome %s cadastrou um problema com sucesso na Orientação de id %s";
	
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

	public static ResponseEntity<CustomReturnMessage> geraRelatorio(
			List<RelatorioProblemaIndividualDTO> problemasDasOrientacoesDoPeriodoAluno,
			List<RelatorioProblemaIndividualDTO> problemasDasOrientacoesDoPeriodoProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

}
