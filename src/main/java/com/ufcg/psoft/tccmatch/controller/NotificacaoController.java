package com.ufcg.psoft.tccmatch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Notificacao;
import com.ufcg.psoft.tccmatch.model.Usuario;
import com.ufcg.psoft.tccmatch.service.NotificacaoService;
import com.ufcg.psoft.tccmatch.service.UsuarioService;
import com.ufcg.psoft.tccmatch.util.ErroAluno;
import com.ufcg.psoft.tccmatch.util.ErroLogin;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class NotificacaoController {
	
	@Autowired
	NotificacaoService notificacaoService;
	
    @Autowired
    private Map<String, UsuarioService> services;
	
	@RequestMapping(value = "/notificacoes/{token}/{tipoUsuario}", method = RequestMethod.GET)
	public ResponseEntity<?> listarNotificacoes(@PathVariable("token") long id, @PathVariable("tipoUsuario") String tipoUsuario) {

    	UsuarioService usuarioService = services.get(tipoUsuario.toUpperCase());
    	
    	if (usuarioService == null) {
    		return ErroLogin.erroServiceIndisponivel(tipoUsuario);
    	}
    	
    	Optional<Usuario> usuarioOp = usuarioService.getById(id);
    	    	
    	if (usuarioOp.isEmpty()) {
    		return ErroLogin.erroTokenInvalido(id);
    	}
    	
    	Usuario usuario = usuarioOp.get();
    	
    	List<String> notificacoes = notificacaoService.listaNotificacoesUsuario(usuario);

		usuarioService.save(usuario);

		return new ResponseEntity<List<String>>(notificacoes, HttpStatus.OK);
	}

}
