package com.ufcg.psoft.tccmatch.service;

import com.ufcg.psoft.tccmatch.DTO.TemaTccAlunoDTO;
import com.ufcg.psoft.tccmatch.DTO.TemaTccCadastradoDTO;
import com.ufcg.psoft.tccmatch.DTO.TemaTccProfessorDTO;
import com.ufcg.psoft.tccmatch.DTO.UsuarioCadastradoDTO;
import com.ufcg.psoft.tccmatch.model.*;
import com.ufcg.psoft.tccmatch.repository.TemaTccRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	public TemaTcc criarTemaTccAluno(TemaTccAlunoDTO temaTccDTO, Usuario usuario) {
		return new TemaTcc(usuario, temaTccDTO.getTitulo(), temaTccDTO.getDescricao(), temaTccDTO.getStatus(),
				areaDeEstudoService.getAreasByNome(temaTccDTO.getAreasDeEstudoRelacionadas()));
	}

	@Override
	public TemaTcc criarTemaTccProfessor(TemaTccProfessorDTO temaTccDTO, Usuario usuario) {
		return new TemaTcc(usuario, temaTccDTO.getTitulo(), temaTccDTO.getDescricao(),
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
			if (!(professorService.getById(tema.getUsuarioCriador().getId()).isEmpty())) {
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
			if (!(alunoService.getById(tema.getUsuarioCriador().getId()).isEmpty())) {
				temasTccAlunos.add(tema);
			}
		}
		return temasTccAlunos;
	}

	@Override
	public List<TemaTcc> getTemasTccProfessor(Long idProfessor) {
		List<TemaTcc> temasTcc = getTemasTcc();
		List<TemaTcc> temasTccProfessor = new ArrayList<TemaTcc>();
		
		for (TemaTcc tema : temasTcc) {
			if (tema.getUsuarioCriadorId().equals(idProfessor)) {
				temasTccProfessor.add(tema);
			}
		}
		
		return temasTccProfessor;
	}

	@Override
	public Optional<Aluno> getAlunoByTema(TemaTcc temaTcc) {
		Long usuarioId = temaTcc.getUsuarioCriadorId();
		Optional<Aluno> alunoOp = alunoService.getById(usuarioId);
		
		return alunoOp;
	}
	
	@Override
	public Optional<Professor> getProfessorByTema(TemaTcc temaTcc) {
		Long usuarioId = temaTcc.getUsuarioCriadorId();
		Optional<Professor> professorOp = professorService.getById(usuarioId);
		
		return professorOp;
	}
	
	@Override
	public boolean verificaCriadorTema(TemaTcc temaTcc, Usuario usuario1, Usuario usuario2) {
		return temaTcc.getUsuarioCriador() == usuario1 || temaTcc.getUsuarioCriador() == usuario2;
	}

	@Override
	public TemaTccCadastradoDTO criarTemaTccCadastradoDTO(TemaTcc temaTcc) {
		List<String> areasDeEstudosRelacionadas = new ArrayList<>();
		Usuario usuario = temaTcc.getUsuarioCriador();
		UsuarioCadastradoDTO usuarioCadastradoDTO = new UsuarioCadastradoDTO(usuario.getNome(), usuario.getUsername(),
				usuario.getEmail(), usuario.getTipoUsuario());

		for (AreaDeEstudo area : temaTcc.getAreasDeEstudoRelacionadas()) {
			areasDeEstudosRelacionadas.add(area.getNome());
		}

		return new TemaTccCadastradoDTO(temaTcc.getTitulo(), temaTcc.getDescricao(), areasDeEstudosRelacionadas, temaTcc.getStatus(), usuarioCadastradoDTO);
	}

	@Override
	public List<TemaTccCadastradoDTO> getTemasTccDTO(List<TemaTcc> listaTemasTcc) {
		List<TemaTccCadastradoDTO> listaTemasDTO = new ArrayList<>();

		for (TemaTcc temaTcc : listaTemasTcc) {
			listaTemasDTO.add(this.criarTemaTccCadastradoDTO(temaTcc));
		}
		return listaTemasDTO;
	}

}
