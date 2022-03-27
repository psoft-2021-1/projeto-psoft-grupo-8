package com.ufcg.psoft.tccmatch.DTO;

public class OrientacaoCadastradaDTO {
	
	private Long id;
	
	private String matriculaAluno;

    private String tituloTema;

    private String semestre;
    
    public OrientacaoCadastradaDTO(Long id, String matriculaAluno, String tituloTema, String semestre) {
		super();
		this.id = id;
		this.matriculaAluno = matriculaAluno;
		this.tituloTema = tituloTema;
		this.semestre = semestre;
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

	public void setTituloTema(String tituloTema) {
		this.tituloTema = tituloTema;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


    
}
