package com.ufcg.psoft.tccmatch.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.model.Aluno;
import com.ufcg.psoft.tccmatch.model.Professor;
import com.ufcg.psoft.tccmatch.model.TemaInteresse;
import com.ufcg.psoft.tccmatch.model.TemaTcc;

public interface TemaInteresseService {
	
	public void save(TemaInteresse temaInteresse);
	
	public TemaInteresse manifestarInteresse(Aluno aluno, Professor professor, TemaTcc temaTcc);
	
	public Optional<TemaInteresse> getById(Long id);

	public List<TemaInteresse> getInteressesRecebidos(Aluno aluno);

}
