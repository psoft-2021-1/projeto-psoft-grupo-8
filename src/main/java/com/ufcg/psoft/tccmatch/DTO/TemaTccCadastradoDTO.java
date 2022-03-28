package com.ufcg.psoft.tccmatch.DTO;

import java.util.List;

public class TemaTccCadastradoDTO {

    private String titulo;

    private String descricao;

    private List<String> areasDeEstudoRelacionadas;

    private String status;

    private UsuarioCadastradoDTO usuarioCadastradoDTO;

    public TemaTccCadastradoDTO(String titulo, String descricao, List<String> areasDeEstudoRelacionadas, String status, UsuarioCadastradoDTO usuarioCadastradoDTO) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.areasDeEstudoRelacionadas = areasDeEstudoRelacionadas;
        this.status = status;
        this.usuarioCadastradoDTO = usuarioCadastradoDTO;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getAreasDeEstudoRelacionadas() {
        return areasDeEstudoRelacionadas;
    }

    public void setAreasDeEstudoRelacionadas(List<String> areasDeEstudoRelacionadas) {
        this.areasDeEstudoRelacionadas = areasDeEstudoRelacionadas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UsuarioCadastradoDTO getUsuarioCadastradoDTO() {
        return usuarioCadastradoDTO;
    }

    public void setUsuarioCadastradoDTO(UsuarioCadastradoDTO usuarioCadastradoDTO) {
        this.usuarioCadastradoDTO = usuarioCadastradoDTO;
    }
}
