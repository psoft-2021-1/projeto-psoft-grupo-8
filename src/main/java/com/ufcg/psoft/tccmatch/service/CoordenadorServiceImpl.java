package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.repository.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordenadorServiceImpl implements  CoordenadorService {

    @Autowired
    private CoordenadorRepository coordenadorRepository;

    @Override
    public String helloWorld() {
        return "Hello, World!";
    }

}
