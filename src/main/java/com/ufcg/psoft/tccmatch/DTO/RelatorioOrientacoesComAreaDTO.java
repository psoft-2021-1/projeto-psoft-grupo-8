package com.ufcg.psoft.tccmatch.DTO;

import java.util.ArrayList;
import java.util.List;

public class RelatorioOrientacoesComAreaDTO {

	List<OrientacaoComAreaDTO> orientacoesEmCurso = new ArrayList<OrientacaoComAreaDTO>();
	
	List<OrientacaoComAreaDTO> orientacoesFinalizadas = new ArrayList<OrientacaoComAreaDTO>();

	public RelatorioOrientacoesComAreaDTO(List<OrientacaoComAreaDTO> orientacoesEmCurso,
			List<OrientacaoComAreaDTO> orientacoesFinalizadas) {
		super();
		this.orientacoesEmCurso = orientacoesEmCurso;
		this.orientacoesFinalizadas = orientacoesFinalizadas;
	}

	public List<OrientacaoComAreaDTO> getOrientacoesEmCurso() {
		return orientacoesEmCurso;
	}

	public void setOrientacoesEmCurso(List<OrientacaoComAreaDTO> orientacoesEmCurso) {
		this.orientacoesEmCurso = orientacoesEmCurso;
	}

	public List<OrientacaoComAreaDTO> getOrientacoesFinalizadas() {
		return orientacoesFinalizadas;
	}

	public void setOrientacoesFinalizadas(List<OrientacaoComAreaDTO> orientacoesFinalizadas) {
		this.orientacoesFinalizadas = orientacoesFinalizadas;
	}
	
	
	
}
