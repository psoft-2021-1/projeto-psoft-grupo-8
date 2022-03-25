package com.ufcg.psoft.tccmatch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.tccmatch.DTO.TemaTccAlunoDTO;
import com.ufcg.psoft.tccmatch.DTO.TemaTccProfessorDTO;
import com.ufcg.psoft.tccmatch.model.Aluno;
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
		return temaTccRepository.findByTitulo(titulo.toUpperCase());
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
			if (!(professorService.findByUsername(tema.getUsernameCriador()).isEmpty())) {
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
			if (!(alunoService.findByUsername(tema.getUsernameCriador()).isEmpty())) {
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
			if (tema.getUsernameCriador().equals(username)) {
				temasTccProfessor.add(tema);
			}
		}
		
		return temasTccProfessor;
	}

	@Override
	public Optional<Aluno> getAlunoTemaTcc(TemaTcc temaTcc) {
		String usernameCriador = temaTcc.getUsernameCriador();
		Optional<Aluno> alunoOp = alunoService.findByUsername(usernameCriador);
		
		return alunoOp;
	}
	
	@Override
	public Optional<Professor> getProfessorTemaTcc(TemaTcc temaTcc) {
		String usernameCriador = temaTcc.getUsernameCriador();
		Optional<Professor> professorOp = professorService.findByUsername(usernameCriador);
		
		return professorOp;
	}

	@Override
	public TemaTcc manifestarInteresseTemaAluno(TemaTcc temaTcc, String usernameProfessor) {
		temaTcc.setUsernameCriador(usernameProfessor);
		return temaTcc;
	}

}
