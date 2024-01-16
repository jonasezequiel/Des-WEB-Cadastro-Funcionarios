package com.funcionarios.funcionarios.model;

import org.springframework.data.annotation.Id;

public class Cargo {

    @Id
    private int id_cargo;
    private String profissao;
    private String descricao;

    public Cargo() {
    }

    public Cargo(int id_cargo, String profissao, String descricao) {
        this.id_cargo = id_cargo;
        this.profissao = profissao;
        this.descricao = descricao;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
