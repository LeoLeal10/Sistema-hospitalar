package br.edu.fesa.model;

import java.time.LocalDate;

public class Usuario {
    private int id;
    private String nome;
    private LocalDate dataNasc;
    private String login;
    private String senha;
    private String tipoUser;
    private int idUser;

    public Usuario() {

    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario( String nome, LocalDate dataNasc, String login,
            String senha, String tipoUser, int idUser) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.login = login;
        this.senha = senha;
        this.tipoUser = tipoUser;
        this.idUser = idUser;
    }

    public Usuario(int id, String nome, LocalDate dataNasc, String login, String senha, String tipoUser, int idUser) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.login = login;
        this.senha = senha;
        this.tipoUser = tipoUser;
        this.idUser = idUser;
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

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
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

    public String getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
