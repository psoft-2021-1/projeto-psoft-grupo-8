package com.ufcg.psoft.tccmatch.DTO;

import java.util.List;

public class RelatorioProblemaGeralDTO {
	
	List<RelatorioProblemaIndividualDTO> problemasDasOrientacoesDoPeriodoAluno;
	
	List<RelatorioProblemaIndividualDTO> problemasDasOrientacoesDoPeriodoProfessor;

	public RelatorioProblemaGeralDTO(List<RelatorioProblemaIndividualDTO> problemasDasOrientacoesDoPeriodoAluno,
			List<RelatorioProblemaIndividualDTO> problemasDasOrientacoesDoPeriodoProfessor) {
		this.problemasDasOrientacoesDoPeriodoAluno = problemasDasOrientacoesDoPeriodoAluno;
		this.problemasDasOrientacoesDoPeriodoProfessor = problemasDasOrientacoesDoPeriodoProfessor;
	}
	
	public List<RelatorioProblemaIndividualDTO> getProblemasDasOrientacoesDoPeriodoAluno() {
		return problemasDasOrientacoesDoPeriodoAluno;
	}

	public void setProblemasDasOrientacoesDoPeriodoAluno(
			List<RelatorioProblemaIndividualDTO> problemasDasOrientacoesDoPeriodoAluno) {
		this.problemasDasOrientacoesDoPeriodoAluno = problemasDasOrientacoesDoPeriodoAluno;
	}

	public List<RelatorioProblemaIndividualDTO> getProblemasDasOrientacoesDoPeriodoProfessor() {
		return problemasDasOrientacoesDoPeriodoProfessor;
	}

	public void setProblemasDasOrientacoesDoPeriodoProfessor(
			List<RelatorioProblemaIndividualDTO> problemasDasOrientacoesDoPeriodoProfessor) {
		this.problemasDasOrientacoesDoPeriodoProfessor = problemasDasOrientacoesDoPeriodoProfessor;
	}
	
}
