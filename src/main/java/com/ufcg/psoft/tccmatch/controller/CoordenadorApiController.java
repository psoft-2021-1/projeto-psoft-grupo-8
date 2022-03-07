package com.ufcg.psoft.tccmatch.controller;

import com.ufcg.psoft.tccmatch.service.CoordenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CoordenadorApiController {

    @Autowired
    CoordenadorService coordenadorService;

    @RequestMapping(value = "/coordenador", method = RequestMethod.GET)
    public ResponseEntity<?> teste() {
        return new ResponseEntity<String>(coordenadorService.helloWorld(), HttpStatus.OK);
    }

}
