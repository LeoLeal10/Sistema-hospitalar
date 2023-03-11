/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.controller;

import static br.edu.fesa.controller.PadraoSistema.irParaTela;
import static br.edu.fesa.controller.PadraoSistema.listaAdmin;
import static br.edu.fesa.controller.PadraoSistema.listaAtendentes;
import static br.edu.fesa.controller.PadraoSistema.listaEnfermeiros;
import static br.edu.fesa.controller.PadraoSistema.listaMedicos;
import br.edu.fesa.model.Administrador;
import br.edu.fesa.model.Atendente;
import br.edu.fesa.model.Enfermeiro;
import br.edu.fesa.model.Medico;
import br.edu.fesa.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListaFuncionariosController implements Initializable{

    @FXML
    private Button btnCadAdmin;

    @FXML
    private Button btnCadAtendente;

    @FXML
    private Button btnCadEnfermeiro;

    @FXML
    private Button btnCadMedico;

    @FXML
    private Button btnCarregar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<Usuario, LocalDate> clDataNasc;

    @FXML
    private TableColumn<Usuario, Integer> clId;

    @FXML
    private TableColumn<Usuario, String> clLogin;

    @FXML
    private TableColumn<Usuario, String> clNome;

    @FXML
    private TableColumn<Usuario, String> clTipoUsuario;

    @FXML
    private TableView<Usuario> tbUsuarios;

    @FXML
    private TextField txtBusca;
    
    private ObservableList<Usuario> observableUsuarios;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criaTabela();
        carregaUsuarios();
        filtraFicha();
    }    
    
    @FXML
    public void criaTabela() {
        clId.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        clNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        clDataNasc.setCellValueFactory(
                new PropertyValueFactory<>("dataNasc"));
        clLogin.setCellValueFactory(
                new PropertyValueFactory<>("login"));
        clTipoUsuario.setCellValueFactory(
                new PropertyValueFactory<>("tipoUser"));      
    }
    
    public void carregaUsuarios() {
        tbUsuarios.getItems().removeAll();
        observableUsuarios = FXCollections.observableArrayList(PadraoSistema.listaUsers);
        tbUsuarios.setItems(observableUsuarios);
    }
    
     public void filtraFicha() {
        FilteredList<Usuario> filteredData = new FilteredList<>(observableUsuarios, b -> true);

        txtBusca.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches name.
                } else if (String.valueOf(user.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches id.
                }
                else if (user.getLogin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches login.
                }
                else if (user.getTipoUser().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches tipo do user.
                } else {
                    return false;
                }
            });
        });

        SortedList<Usuario> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tbUsuarios.comparatorProperty());

        tbUsuarios.setItems(sortedData);
    }
    
    public void novoAdmin() {
        try {
            App.setRoot("NovoAdmin");
        } catch (IOException e) {
            PadraoSistema.exibeErro("Não foi possivel abrir a tela de NOVO ADMINISTRADOR.");
        }
    }

    public void novoAtendente() {
        try {
            App.setRoot("novoAtendente");
        } catch (IOException e) {
            PadraoSistema.exibeErro("Não foi possivel abrir a tela de NOVO ATENDENTE.");
        }
    }

    public void novoEnfermeiro() {
        try {
            App.setRoot("novoEnfermeiro");
        } catch (IOException e) {
            PadraoSistema.exibeErro("Não foi possivel abrir a tela de NOVO ENFERMEIRO.");
        }
    }

    public void novoMedico() {
        try {
            App.setRoot("novoMedico");
        } catch (IOException e) {
            PadraoSistema.exibeErro("Não foi possivel abrir a tela de NOVO MÉDICO.");
        }
    }
    
    public void cliqueEditarFichaMedica() {
        try {
            if (tbUsuarios.getSelectionModel().getSelectedItem() != null) {
                Usuario x = tbUsuarios.getSelectionModel().getSelectedItem();
                
                //Cadeia de if para jogar no formulario certo de edição!
                if(x.getTipoUser()=="Médico"){
                    EditarMedicoController.user  = x;
                    App.setRoot("EditarMedico");
                }
                else if(x.getTipoUser()=="Enfermeiro"){
                    EditarEnfermeiroController.user  = x;
                    App.setRoot("EditarEnfermeiro");
                }
                else if(x.getTipoUser()=="Atendente"){
                    EditarAtendenteController.user  = x;
                    App.setRoot("EditarAtendente");
                    
                }
                else if(x.getTipoUser()=="Administrador"){
                    EditarAdminController.user  = x;
                    App.setRoot("EditarAdmin");
                }
                //se o tipo não bater, ele joga mensagem de erro
                else{
                    PadraoSistema.exibeErro("Não foi possivel relacionar o tipo desse usuário!");
                }

            } else {
                PadraoSistema.exibeErro("Selecione um usuário!");
            }

        } catch (IOException e) {
            PadraoSistema.exibeErro("Não foi possivel abrir a tela de EDITAR USUÁRIO.");
        }
    }
    
    public void avisoRemover() {
        Usuario user = tbUsuarios.getSelectionModel().getSelectedItem();
        if (user != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletar");
            alert.setHeaderText("Deseja deletar o Usuário?");
            alert.setContentText(user.toString());
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    removeUsuario(user);
                    carregaUsuarios();
                }
            });
        } else {
            PadraoSistema.exibeErro("Selecione um usuário!");
        }
    }

    public void removeUsuario(Usuario user) {
        //Cadeia de if para remover o tipo certo!
        if (user.getTipoUser() == "Médico") {
            for (Medico x : listaMedicos) {
                if (user.getIdUser() == x.getId()) {
                    PadraoSistema.listaMedicos.remove(x);
                    PadraoSistema.listaUsers.remove(user);
                    carregaUsuarios();
                }
            }
        } else if (user.getTipoUser() == "Enfermeiro") {
            for (Enfermeiro x : listaEnfermeiros) {
                if (user.getIdUser() == x.getId()) {
                    PadraoSistema.listaEnfermeiros.remove(x);
                    PadraoSistema.listaUsers.remove(user);
                    carregaUsuarios();
                }
            }
        } else if (user.getTipoUser() == "Atendente") {
            for (Atendente x : listaAtendentes) {
                if (user.getIdUser() == x.getId()) {
                    PadraoSistema.listaAtendentes.remove(x);
                    PadraoSistema.listaUsers.remove(user);
                    carregaUsuarios();
                }
            }
        } else if (user.getTipoUser() == "Administrador") {
            for (Administrador x : listaAdmin) {
                if (user.getIdUser() == x.getId()) {
                    PadraoSistema.listaAdmin.remove(x);
                    PadraoSistema.listaUsers.remove(user);
                    carregaUsuarios();
                    //deve-se ir para a tela de login se o usuario escolhido for deslogado!
                }
            }
        } //se o tipo não bater, ele joga mensagem de erro
        else {
            PadraoSistema.exibeErro("Não foi possivel relacionar o tipo desse usuário!");
        }

    }

 
    public void voltarTela() {
        PadraoSistema.irParaTela("Administrador");
    }
    
}
