package com.ufcg.psoft.tccmatch.DTO;

public class OrientacaoCadastradaDTO {
	
	private Long id;
	
	private String matriculaAluno;

	private String cpfProfessor;

    private String tituloTema;

    private String semestre;

	private boolean isFinalizada;

    public OrientacaoCadastradaDTO(Long id, String matriculaAluno, String cpfProfessor, String tituloTema, String semestre, boolean isFinalizada) {
		super();
		this.id = id;
		this.matriculaAluno = matriculaAluno;
		this.cpfProfessor = cpfProfessor;
		this.tituloTema = tituloTema;
		this.semestre = semestre;
		this.isFinalizada = isFinalizada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatriculaAluno() {
		return matriculaAluno;
	}

	public void setMatriculaAluno(String matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
	}

	public String getTituloTema() {
		return tituloTema;
	}

	public String getCpfProfessor() {
		return cpfProfessor;
	}

	public void setCpfProfessor(String cpfProfessor) {
		this.cpfProfessor = cpfProfessor;
	}

	public void setTituloTema(String tituloTema) {
		this.tituloTema = tituloTema;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public boolean isFinalizada() {
		return isFinalizada;
	}

	public void setFinalizada(boolean finalizada) {
		isFinalizada = finalizada;
	}
}
