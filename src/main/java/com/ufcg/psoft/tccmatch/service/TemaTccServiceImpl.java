package com.ufcg.psoft.tccmatch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.TemaTccAlunoDTO;
import com.ufcg.psoft.tccmatch.DTO.TemaTccProfessorDTO;
import com.ufcg.psoft.tccmatch.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.tccmatch.model.TemaTcc;
import com.ufcg.psoft.tccmatch.repository.TemaTccRepository;

@Service
public class TemaTccServiceImpl implements TemaTccService {

	@Autowired
	private TemaTccRepository temaTccRepository;

	@Autowired
	private AreaDeEstudoService areaDeEstudoService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private AlunoService alunoService;

	// TODO Revisar forma que estamos criando os temas.
	@Override
	public TemaTcc criarTemaTccAluno(TemaTccAlunoDTO temaTccDTO, String username) {
		return new TemaTcc(username, temaTccDTO.getTitulo(), temaTccDTO.getDescricao(), temaTccDTO.getStatus(),
				areaDeEstudoService.getAreasByNome(temaTccDTO.getAreasDeEstudoRelacionadas()));
	}

	@Override
	public TemaTcc criarTemaTccProfessor(TemaTccProfessorDTO temaTccDTO, String username) {
		return new TemaTcc(username, temaTccDTO.getTitulo(), temaTccDTO.getDescricao(),
				areaDeEstudoService.getAreasByNome(temaTccDTO.getAreasDeEstudoRelacionadas()));
	}

	@Override
	public void save(TemaTcc temaTcc) {
		temaTccRepository.save(temaTcc);
	}

	@Override
	public Optional<TemaTcc> getByTitulo(String titulo) {
		return temaTccRepository.findByTitulo(titulo);
	}

	@Override
	public List<TemaTcc> getTemasTcc() {
		return temaTccRepository.findAll();
	}

	@Override
	public List<TemaTcc> getTemasByTitulo(String[] temasTitulos) {
		List<TemaTcc> temasTcc = new ArrayList<TemaTcc>();

		for (String titulo : temasTitulos) {
			TemaTcc temaTcc = getByTitulo(titulo.toUpperCase()).get();
			temasTcc.add(temaTcc);
		}
		return temasTcc;
	}

	@Override
	public List<TemaTcc> getTemasTccProfessores() {
		List<TemaTcc> temasTcc = getTemasTcc();
		List<TemaTcc> temasTccProfessores = new ArrayList<>();

		for (TemaTcc tema : temasTcc) {
			if (!(professorService.findByUsername(tema.getUsername()).isEmpty())) {
				temasTccProfessores.add(tema);
			}
		}
		return temasTccProfessores;
	}

	@Override
	public List<TemaTcc> getTemasTccAlunos() {
		List<TemaTcc> temasTcc = getTemasTcc();
		List<TemaTcc> temasTccAlunos = new ArrayList<>();

		for (TemaTcc tema : temasTcc) {
			if (!(alunoService.findByUsername(tema.getUsername()).isEmpty())) {
				temasTccAlunos.add(tema);
			}
		}
		return temasTccAlunos;
	}

	@Override
	public List<TemaTcc> getTemasTccProfessor(String username) {
		List<TemaTcc> temasTcc = getTemasTcc();
		List<TemaTcc> temasTccProfessor = new ArrayList<TemaTcc>();
		
		for (TemaTcc tema : temasTcc) {
			if (tema.getUsername().equals(username)) {
				temasTccProfessor.add(tema);
			}
		}
		
		return temasTccProfessor;
	}

	@Override
	public boolean isTemaTccAluno(TemaTcc temaTcc) {
		for (TemaTcc temaTccAluno : getTemasTccAlunos()) {
			if (temaTcc.equals(temaTccAluno)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public TemaTcc manifestarInteresseTemaAluno(TemaTcc temaTcc, String usernameProfessor) {
		temaTcc.setUsername(usernameProfessor);
		return temaTcc;
	}

}
