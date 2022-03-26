package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.TemaInteresse;
import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.repository.SolicitacaoOrientacaoRepository;
import com.ufcg.psoft.tccmatch.repository.TemaInteresseRepository;

@Service
public class TemaInteresseServiceImpl implements TemaInteresseService {
	
	@Autowired
	TemaInteresseRepository temaInteresseRepository;

	@Override
	public void save(TemaInteresse temaInteresse) {
		temaInteresseRepository.save(temaInteresse);
	}

	@Override
	public TemaInteresse manifestarInteresse(Aluno aluno, Professor professorInteressado, TemaTcc temaTcc) {
		TemaInteresse temaInteresse = new TemaInteresse(aluno, professorInteressado, temaTcc);
		return temaInteresse;
	}

	@Override
	public Optional<TemaInteresse> getById(Long id) {
		return temaInteresseRepository.findById(id);
	}

	@Override
	public List<TemaInteresse> getInteressesRecebidos(Aluno aluno) {
		return temaInteresseRepository.findAllByAluno(aluno);
	}

}
