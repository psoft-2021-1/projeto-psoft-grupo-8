package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Orientacao;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.repository.OrientacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrientacaoServiceImpl implements OrientacaoService {

    @Autowired
    OrientacaoRepository orientacaoRepository;


    @Override
    public Orientacao cadastrarOrientacao(Aluno aluno, TemaTcc temaTcc, Professor professor, String semestre) {
        Orientacao orientacao = new Orientacao(aluno, temaTcc, professor, semestre);
        return orientacao;
    }

    @Override
    public void save(Orientacao orientacao) {
        orientacaoRepository.save(orientacao);
    }
    
    @Override
    public List<Orientacao> findAllOrientacaoByProfessor(Professor professor) {
    	return orientacaoRepository.findAllByProfessor(professor);
    }
}
