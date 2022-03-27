package com.ufcg.psoft.tccmatch.DTO;

import java.util.ArrayList;
import java.util.List;

import com.ufcg.psoft.tccmatch.model.AreaDeEstudo;

public class OrientacaoComAreaDTO extends OrientacaoDTO {

	List<AreaDeEstudoDTO> areasDeEstudoDTO;
	
	public OrientacaoComAreaDTO(Long matriculaAluno, String tituloTema, Long cpfProfessor, String semestre, List<AreaDeEstudo> areasDeEstudo) {
		super(matriculaAluno, tituloTema, cpfProfessor, semestre);
		List<AreaDeEstudoDTO> novasAreasDeEstudoDTO = new ArrayList<>();
		for(AreaDeEstudo areaDeEstudo: areasDeEstudo) {
			AreaDeEstudoDTO areaDeEstudoDTO = new AreaDeEstudoDTO(areaDeEstudo.getNome());
			novasAreasDeEstudoDTO.add(areaDeEstudoDTO);
		}
		this.setAreaDeEstudoDTO(novasAreasDeEstudoDTO);
	}

	public List<AreaDeEstudoDTO> getAreasDeEstudoDTO() {
		return areasDeEstudoDTO;
	}

	public void setAreaDeEstudoDTO(List<AreaDeEstudoDTO> areasDeEstudoDTO) {
		this.areasDeEstudoDTO = areasDeEstudoDTO;
	}
	
	
}
