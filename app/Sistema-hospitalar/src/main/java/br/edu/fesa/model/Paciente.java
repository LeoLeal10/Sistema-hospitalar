package br.edu.fesa.model;

import java.time.LocalDate;

public class Paciente {
    
    private int id;
    private String nome;
    private LocalDate dataNasc;
    private String cpf;
    private int idConvenio;
    private String cep;
    private String endereco;
    private int numero;
    private String telefone;

    public Paciente() {
    }

    public Paciente(String nome, LocalDate dataNasc, String cpf, int idConvenio,
            String cep, String endereco, int numero, String telefone) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.idConvenio = idConvenio;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.telefone = telefone;
    }

    public Paciente(int id, String nome, LocalDate dataNasc, String cpf, int idConvenio, String cep, String endereco, int numero, String telefone) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.idConvenio = idConvenio;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.telefone = telefone;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(int idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Paciente{" + "id=" + id + ", nome=" + nome + ", dataNasc=" + dataNasc +
                ", cpf=" + cpf + ", idConvenio=" + idConvenio + ", cep=" + cep + 
                ", endereco=" + endereco + ", numero=" + numero + ", telefone=" + telefone + '}';
    }

    
}
