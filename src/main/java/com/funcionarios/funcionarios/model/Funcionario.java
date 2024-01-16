package com.funcionarios.funcionarios.model;

import org.springframework.data.annotation.Id;

public class Funcionario {

    @Id
    private int id;
    private String nome;
    private String cpf;
    private String login;
    private String senha;
    private String cargo;
    private String descricao;

    public Funcionario() {
    }

    public Funcionario(int id, String nome, String cpf, String login, String senha, String cargo, String descricao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
