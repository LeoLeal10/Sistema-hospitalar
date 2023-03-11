package br.edu.fesa.controller;

import br.edu.fesa.model.Administrador;
import br.edu.fesa.model.Atendente;
import br.edu.fesa.model.Convenio;
import br.edu.fesa.model.Enfermeiro;
import br.edu.fesa.model.FichaMedica;
import br.edu.fesa.model.Medico;
import br.edu.fesa.model.Paciente;
import br.edu.fesa.model.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.scene.chart.XYChart;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        criaAtendentes();
        criaEnfermeiros();
        criaMedicos();
        criaPacientes();
        criaUsuarios();
        criaAdministrador();
        criaConvenios();
        criaEspecialidades();
        criaFichaMedica();

        scene = new Scene(loadFXML("Login"), 640, 480);
        //stage.setFullScreen(true); //tela cheia
        stage.setMaximized(true); //tela cheia com menu
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public void criaEspecialidades() {
        PadraoSistema.listaEspecialidades.add("Clínica Médica");
        PadraoSistema.listaEspecialidades.add("Ortopedia");
        PadraoSistema.listaEspecialidades.add("Otorrinolaringologia");
        PadraoSistema.listaEspecialidades.add("Pediatria");
        PadraoSistema.listaEspecialidades.add("Ginicologista e Obstetrícia");
        PadraoSistema.listaEspecialidades.add("Cardiologia");
        PadraoSistema.listaEspecialidades.add("Oftamologia");
        PadraoSistema.listaEspecialidades.add("Cirurgia Geral");
    }

    public void criaUsuarios() {
        PadraoSistema.listaUsers.add(new Usuario(1, "Leonardo", LocalDate.now(),
                "leonardo", "12345", "Médico", 2));

        PadraoSistema.listaUsers.add(new Usuario(1, "Lucas", LocalDate.now(),
                "lucas", "12345", "Enfermeiro", 2));

        PadraoSistema.listaUsers.add(new Usuario(1, "Luiza", LocalDate.now(),
                "luiza", "12345", "Atendente", 1));

        PadraoSistema.listaUsers.add(new Usuario(1, "Gabriel", LocalDate.now(),
                "gabriel", "12345", "Administrador", 1));

    }

    public void criaPacientes() {
        PadraoSistema.listPacientes.add(new Paciente(1, "Leonardo", LocalDate.now(),
                "123456789-10", 1, "09578-160", "Rua um",
                10, "4002-8922"));
        PadraoSistema.listPacientes.add(new Paciente(2, "Lucas", LocalDate.MIN,
                "123456789-10", 2, "09578-250", "Rua dois",
                20, "94002-8922"));
    }

    public void criaMedicos() {
        PadraoSistema.listaMedicos.add(new Medico("12345", "Dentista", 1));
        PadraoSistema.listaMedicos.add(new Medico("54678", "Pediatra", 2));
    }

    public void criaEnfermeiros() {
        PadraoSistema.listaEnfermeiros.add(new Enfermeiro("61641", 1));
        PadraoSistema.listaEnfermeiros.add(new Enfermeiro("61642", 2));
    }

    public void criaAtendentes() {
        PadraoSistema.listaAtendentes.add(new Atendente(1));
    }

    public void criaAdministrador() {
        PadraoSistema.listaAdmin.add(new Administrador(1));
    }

    public void criaConvenios() {
        PadraoSistema.listConvenios.add(new Convenio(1, "SUS"));
        PadraoSistema.listConvenios.add(new Convenio(2, "Sulamerica"));
    }

    public void criaFichaMedica() {
        int id = 1;
        for (int x = -6; x <= 0; x++) {
            LocalDate date = LocalDate.now().plusDays(x);

            PadraoSistema.listFichasMedica.add(new FichaMedica(1, id,
                    id, id, id, id,
                    "p30", 30, "Vermelho", "",
                    "", "", LocalDate.now().plusDays(x),
                    LocalTime.now(), LocalDate.now().plusDays(x)));

                        PadraoSistema.listFichasMedica.add(new FichaMedica(1, id,
                    id, id, id, id,
                    "p30", 30, "Vermelho", "",
                    "", "", LocalDate.now().plusDays(x),
                    LocalTime.now(), LocalDate.now().plusDays(x)));

            PadraoSistema.listFichasMedica.add(new FichaMedica(1, id,
                    id + 1, id + 1, id + 1, id + 1,
                    "p30", 30, "Verde", "",
                    "", "", LocalDate.now().plusDays(x),
                    LocalTime.now(), LocalDate.now().plusDays(x)));

            PadraoSistema.listFichasMedica.add(new FichaMedica(1, id,
                    id + 1, id + 1, id + 1, id + 1,
                    "p30", 30, "Amarelo", "",
                    "", "", LocalDate.now().plusDays(x),
                    LocalTime.now(), LocalDate.now().plusDays(x)));
        }
        
        for (int x =0; x < 5; x++) {
            PadraoSistema.listFichasMedica.add(new FichaMedica(1, id,
                    id + 1, id + 1, id + 1, id + 1,
                    null, 0,  null, null,
                    "", "", LocalDate.now(),
                    LocalTime.now(), LocalDate.now()));
        }
    }

}
