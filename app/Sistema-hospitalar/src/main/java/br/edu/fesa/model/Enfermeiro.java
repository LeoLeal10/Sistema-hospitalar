package br.edu.fesa.model;

public class Enfermeiro {

    private String coren;
    private int id;

    public Enfermeiro() {
    }

    public Enfermeiro(String coren) {
        this.coren = coren;
    }

    public Enfermeiro(String coren, int id) {
        this.coren = coren;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoren() {
        return coren;
    }

    public void setCoren(String coren) {
        this.coren = coren;
    }

}
