package br.edu.fesa.controller;

import br.edu.fesa.model.Administrador;
import br.edu.fesa.model.Atendente;
import br.edu.fesa.model.Convenio;
import br.edu.fesa.model.Enfermeiro;
import br.edu.fesa.model.FichaMedica;
import br.edu.fesa.model.Medico;
import br.edu.fesa.model.Paciente;
import br.edu.fesa.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class PadraoSistema {

    public static List<Paciente> listPacientes = new ArrayList();

    public static List<FichaMedica> listFichasMedica = new ArrayList();

    public static List<Convenio> listConvenios = new ArrayList();

    public static List<Usuario> listaUsers = new ArrayList();

    public static List<Medico> listaMedicos = new ArrayList();

    public static List<Enfermeiro> listaEnfermeiros = new ArrayList();

    public static List<Atendente> listaAtendentes = new ArrayList();

    public static List<Administrador> listaAdmin = new ArrayList();
    
    public static List<String> listaEspecialidades = new ArrayList();

    public static Usuario usuarioLogado = new Usuario();

    public static void exibeErro(String mensagem) {
        exibeMensagem(Alert.AlertType.ERROR, "ERRO",
                "Ocorreu um erro ao executar!", mensagem);
    }

    public static void exibeSucesso(String mensagem) {
        exibeMensagem(Alert.AlertType.INFORMATION, "SUCESSO",
                "Cadastro efetuado!", mensagem);
    }

    public static void exibeMensagem(Alert.AlertType TYPE, String titulo, String info, String mensagem) {
        Alert alert = new Alert(TYPE);
        alert.setTitle(titulo);
        alert.setHeaderText(info);
        alert.setContentText(mensagem);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            } else if (rs == ButtonType.CANCEL) {
                System.out.println("Pressed CANCEL.");
            } else if (rs == ButtonType.CLOSE) {
                System.out.println("Pressed CLOSE.");
            }
        });
    }

    public static void irParaTela(String tela) {
        try {
            App.setRoot(tela);
        } catch (Exception e) {
            PadraoSistema.exibeErro("Não foi possível ir para tela: " + tela);
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public static void confirmarSaida() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("SAIR");
        alert.setHeaderText("Deseja sair do sistema?");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                PadraoSistema.usuarioLogado = null;
                irParaTela("Login");                
            }
        });
    }
}
