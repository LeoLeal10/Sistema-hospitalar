/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.controller;

import br.edu.fesa.model.Administrador;
import br.edu.fesa.model.Usuario;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


public class NovoAdminController implements Initializable {

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnSalvar;

    @FXML
    private DatePicker dpDataNasc;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void criarAdmin() {
        if(valida()) {   
            Usuario x  = new Usuario(666, txtNome.getText(),
                    dpDataNasc.getValue(),txtLogin.getText(),
                    txtSenha.getText(), "Administrador",
                    666);
            Administrador y = new Administrador(666);
            PadraoSistema.listaUsers.add(x);
            PadraoSistema.listaAdmin.add(y);
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
    

    public void voltarTela() {
        PadraoSistema.irParaTela("ListaFuncionarios");
    }

}
