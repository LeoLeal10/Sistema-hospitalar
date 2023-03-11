package br.edu.fesa.model;

public class Medico {

    private String crm;
    private String especialidade;
    private int id;

    public Medico() {
    }

    public Medico(String crm, String especialidade) {
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico(String crm, String especialidade, int id) {
        this.crm = crm;
        this.especialidade = especialidade;
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
