package br.edu.fesa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FichaMedica {

    private int id;
    private Integer idPaciente;
    private Integer idAtendente;
    private Integer idEnfermeiro;
    private Integer idMedico;
    private String pressao;
    private int temperatura;
    private String classificacao;
    private String diagnostico;
    private String medicacao;
    private LocalTime horarioAtendimento;
    private LocalDate diaAtendimento;
    private String nomePaciente;
    private LocalDate dataNasc;

    public FichaMedica() {
    }

    public FichaMedica(int id, Integer idPaciente, Integer idAtendente, Integer idEnfermeiro,
            Integer idEnfermeiroDois, Integer idMedico, String pressao,
            int temperatura, String classificacao, String diagnosttico,
            String medicacao, String nomePaciente,LocalDate dataNasc, 
            LocalTime horarioAtendimento) {
        this.idPaciente = idPaciente;
        this.idAtendente = idAtendente;
        this.idEnfermeiro = idEnfermeiro;
        this.idMedico = idMedico;
        this.pressao = pressao;
        this.temperatura = temperatura;
        this.classificacao = classificacao;
        this.diagnostico = diagnosttico;
        this.medicacao = medicacao;
        this.nomePaciente = nomePaciente;
        this.dataNasc = dataNasc;
        this.horarioAtendimento = horarioAtendimento;
    }

    public FichaMedica(int id, Integer idPaciente, Integer idAtendente, Integer idEnfermeiro,
            Integer idEnfermeiroDois, Integer idMedico, String pressao,
            int temperatura, String classificacao, String diagnosttico,
            String medicacao, String nomePaciente, LocalDate dataNasc,
            LocalTime horarioAtendimento, LocalDate diaAtendimento) {
        this.idPaciente = idPaciente;
        this.idAtendente = idAtendente;
        this.idEnfermeiro = idEnfermeiro;
        this.idMedico = idMedico;
        this.pressao = pressao;
        this.temperatura = temperatura;
        this.classificacao = classificacao;
        this.diagnostico = diagnosttico;
        this.medicacao = medicacao;
        this.nomePaciente = nomePaciente;
        this.dataNasc = dataNasc;
        this.horarioAtendimento = horarioAtendimento;
        this.diaAtendimento = diaAtendimento;
    }

    public FichaMedica(Integer idPaciente, Integer idAtendente,
            String nomePaciente, LocalDate dataNasc) {
        this.idPaciente = idPaciente;
        this.idAtendente = idAtendente;
        this.nomePaciente = nomePaciente;
        this.dataNasc = dataNasc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdAtendente() {
        return idAtendente;
    }

    public void setIdAtendente(Integer idAtendente) {
        this.idAtendente = idAtendente;
    }

    public Integer getIdEnfermeiro() {
        return idEnfermeiro;
    }

    public void setIdEnfermeiro(Integer idEnfermeiro) {
        this.idEnfermeiro = idEnfermeiro;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public String getPressao() {
        return pressao;
    }

    public void setPressao(String pressao) {
        this.pressao = pressao;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnosttico) {
        this.diagnostico = diagnosttico;
    }

    public String getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String medicacao) {
        this.medicacao = medicacao;
    }

    public LocalTime getHorarioAtendimento() {
        return horarioAtendimento;
    }

    public void setHorarioAtendimento(LocalTime horarioAtendimento) {
        this.horarioAtendimento = horarioAtendimento;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public LocalDate getDiaAtendimento() {
        return diaAtendimento;
    }

    public void setDiaAtendimento(LocalDate diaAtendimento) {
        this.diaAtendimento = diaAtendimento;
    }

}
