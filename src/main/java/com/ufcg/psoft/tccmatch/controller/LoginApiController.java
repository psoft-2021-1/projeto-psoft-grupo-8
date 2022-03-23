package com.ufcg.psoft.tccmatch.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.psoft.tccmatch.DTO.LoginDTO;
import com.ufcg.psoft.tccmatch.model.Usuario;
import com.ufcg.psoft.tccmatch.service.UsuarioService;
import com.ufcg.psoft.tccmatch.util.ErroLogin;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginApiController {
	
    @Autowired
    private Map<String, UsuarioService> services;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> fazerLogin(@RequestBody LoginDTO loginDTO, UriComponentsBuilder ucBuilder) {
    	
    	UsuarioService usuarioService = services.get(loginDTO.getTipoUsuario());
    	
    	if (usuarioService == null) {
    		return ErroLogin.erroServiceIndisponivel(loginDTO.getTipoUsuario());
    	}
    	
    	Optional<Usuario> usuarioOp = usuarioService.findByUsername(loginDTO.getUsername());
    	    	
    	if (usuarioOp.isEmpty()) {
    		return ErroLogin.erroUsernameSenhaIncorretos();
    	}
    	
    	Usuario usuario = usuarioOp.get();
    	
    	if (!usuario.getSenha().equals(loginDTO.getSenha())) {
    		return ErroLogin.erroUsernameSenhaIncorretos();
    	} 
    	
        return new ResponseEntity<Long>(usuario.getId(), HttpStatus.OK);
    }

}
