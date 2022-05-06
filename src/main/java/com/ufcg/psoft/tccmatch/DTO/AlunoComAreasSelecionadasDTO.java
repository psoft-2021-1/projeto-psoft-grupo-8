package com.ufcg.psoft.tccmatch.DTO;

public class AlunoComAreasSelecionadasDTO {

    private String nome;

    private String username;

    private String email;

    private AreasSelecionadasDTO areasSelecionadasDTO;

    public AlunoComAreasSelecionadasDTO(String nome, String username, String email,
                                        AreasSelecionadasDTO areasSelecionadasDTO) {
        super();
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.areasSelecionadasDTO = areasSelecionadasDTO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AreasSelecionadasDTO getAreasSelecionadasDTO() {
        return areasSelecionadasDTO;
    }

    public void setAreasSelecionadasDTO(AreasSelecionadasDTO areasSelecionadasDTO) {
        this.areasSelecionadasDTO = areasSelecionadasDTO;
    }

}