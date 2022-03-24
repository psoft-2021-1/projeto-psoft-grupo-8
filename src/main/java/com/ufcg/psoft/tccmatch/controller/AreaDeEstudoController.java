package com.ufcg.psoft.tccmatch.controller;

import java.util.List;
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

import com.ufcg.psoft.tccmatch.DTO.AreaDeEstudoDTO;
import com.ufcg.psoft.tccmatch.DTO.AreasSelecionadasDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;
import com.ufcg.psoft.tccmatch.model.Coordenador;
import com.ufcg.psoft.tccmatch.model.Usuario;
import com.ufcg.psoft.tccmatch.service.AlunoService;
import com.ufcg.psoft.tccmatch.service.AreaDeEstudoService;
import com.ufcg.psoft.tccmatch.service.CoordenadorService;
import com.ufcg.psoft.tccmatch.service.UsuarioService;
import com.ufcg.psoft.tccmatch.util.ErroAreaDeEstudo;
import com.ufcg.psoft.tccmatch.util.ErroCoordenador;
import com.ufcg.psoft.tccmatch.util.ErroLogin;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class AreaDeEstudoController {
	
    @Autowired
    AreaDeEstudoService areaDeEstudoService;
    
    @Autowired
	CoordenadorService coordenadorService;
    
    @Autowired
    AlunoService alunoService;
    
    @Autowired
    private Map<String, UsuarioService> services;
    
    @RequestMapping(value = "/adm/areaDeEstudo/{tokenCoordenador}", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarAreaDeEstudo(@RequestBody AreaDeEstudoDTO areaDeEstudoDTO, UriComponentsBuilder ucBuilder,
    											   @PathVariable("tokenCoordenador") long idCoordenador) {
    	 
    	Optional<Coordenador> coordenadorOp = coordenadorService.getById(idCoordenador);
    	
    	if (coordenadorOp.isEmpty()) {
    		return ErroCoordenador.erroCoordenadorNaoCadastrado(idCoordenador);
    	}  	
    	
    	Optional<AreaDeEstudo> areaDeEstudoOp = areaDeEstudoService.getByNome(areaDeEstudoDTO.getNome());
    	
    	if (areaDeEstudoOp.isPresent()) {
    		return ErroAreaDeEstudo.erroAreaDeEstudoJaCadastrada(areaDeEstudoDTO.getNome());
    	}
    	
    	AreaDeEstudo areaDeEstudo = areaDeEstudoService.criarAreaDeEstudo(areaDeEstudoDTO);
    	areaDeEstudoService.save(areaDeEstudo);
    	
    	return new ResponseEntity<AreaDeEstudo>(areaDeEstudo, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/areasDeEstudo/{token}/{tipoUsuario}", method = RequestMethod.GET)
    public ResponseEntity<?> listarAreasDeEstudo(@PathVariable("token") long id, @PathVariable("tipoUsuario") String tipoUsuario) {
    	 
    	UsuarioService service = services.get(tipoUsuario.toUpperCase());
    	
    	if (service == null) {
    		return ErroLogin.erroServiceIndisponivel(tipoUsuario);
    	}
    	
    	Optional<Usuario> usuarioOp = service.getById(id);
    	    	
    	if (usuarioOp.isEmpty()) {
    		return ErroLogin.erroTokenInvalido(id);
    	}
    	
    	List<AreaDeEstudo> areasDeEstudo = areaDeEstudoService.listarAreasDeEstudo();
    	
    	if (areasDeEstudo.isEmpty()) {
    		return ErroAreaDeEstudo.erroAreasDeEstudoNaoCadastradas();
    	}
    	
    	return new ResponseEntity<List<AreaDeEstudo>>(areasDeEstudo, HttpStatus.OK);
    }
  
}
