/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.controller;

import br.edu.fesa.model.Medico;
import br.edu.fesa.model.Usuario;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


public class NovoMedicoController implements Initializable {

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @FXML
    private ChoiceBox<String> cbEspecialidade;

    @FXML
    private DatePicker dpDataNasc;

    @FXML
    private TextField txtCrm;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtSenhaConfirm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaCbEspecialidade();
    }

    public void voltarTela() {
        PadraoSistema.irParaTela("ListaFuncionarios");
    }
    
    public void carregaCbEspecialidade() {
        cbEspecialidade.getItems().removeAll();
        cbEspecialidade.setItems(FXCollections.observableArrayList(PadraoSistema.listaEspecialidades));        
    }
    
    public void criarMedico() {
        if(valida()) {   
            Usuario x  = new Usuario(666, txtNome.getText(),
                    dpDataNasc.getValue(),txtLogin.getText(),
                    txtSenha.getText(), "Médico",
                    666);
            Medico y = new Medico(txtCrm.getText(),cbEspecialidade.getSelectionModel().getSelectedItem(),666);
            PadraoSistema.listaUsers.add(x);
            PadraoSistema.listaMedicos.add(y);
            PadraoSistema.exibeSucesso(x.toString());
            voltarTela();
        }
            
    }
    
    public Boolean valida() {

        Boolean validacao = false;

        try {
            if (txtNome.getText().isEmpty()) {
                throw new Exception("Não há nome preenchido!");
            }
            if (txtLogin.getText().isEmpty()) {
                throw new Exception("Defina um Login!");
            }
            if (txtSenha.getText().isEmpty()) {
                throw new Exception("Defina uma senha!");
            }
            if (txtCrm.getText().isEmpty()) {
                throw new Exception("Defina o CRM!");
            }
            if(cbEspecialidade.getSelectionModel().getSelectedItem() == null) {
                throw new Exception("Selecione uma Especialidade!");
            }
            if (dpDataNasc.getValue() == null || dpDataNasc.getValue().isAfter(LocalDate.now())) {
                throw new Exception("Insira uma data válida!");
            }
            validacao = true;
        } catch (Exception e) {
            PadraoSistema.exibeErro(e.getMessage());
        } finally {
            return validacao;
        }           
    }

    
}
