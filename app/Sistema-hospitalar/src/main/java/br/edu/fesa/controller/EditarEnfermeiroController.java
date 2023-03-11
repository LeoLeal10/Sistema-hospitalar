/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.controller;

import static br.edu.fesa.controller.PadraoSistema.listaEnfermeiros;
import br.edu.fesa.model.Enfermeiro;
import br.edu.fesa.model.Usuario;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author sllui
 */
public class EditarEnfermeiroController implements Initializable {

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @FXML
    private DatePicker dpDataNasc;

    @FXML
    private TextField txtCoren;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;
    
    public static Usuario user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaEnfermeiro();
    }    
    
    public void voltarTela() {
        PadraoSistema.irParaTela("ListaFuncionarios");
    }
    
    public void carregaEnfermeiro(){
        txtNome.setText(user.getNome());
        dpDataNasc.setValue(user.getDataNasc());
        txtLogin.setText(user.getLogin());
        txtSenha.setText(user.getSenha());
        for (Enfermeiro enfermeiroUser : listaEnfermeiros) {
                if (user.getIdUser() == user.getId()) {
                    txtCoren.setText(enfermeiroUser.getCoren());
                }
            }
    }
    
    public void salvarEnfermeiro() {
        if(valida()) {   
            Usuario x  = new Usuario(user.getId(), txtNome.getText(),
                    dpDataNasc.getValue(),txtLogin.getText(),
                    txtSenha.getText(), "Enfermeiro",
                    user.getIdUser());
            Enfermeiro y = new Enfermeiro(txtCoren.getText(),user.getIdUser());
            for (Enfermeiro enfermeiroUser : listaEnfermeiros) {
                if (user.getIdUser() == enfermeiroUser.getId()) {
                    PadraoSistema.listaEnfermeiros.remove(enfermeiroUser);
                    PadraoSistema.listaUsers.remove(user);
                }
            }
            PadraoSistema.listaUsers.add(x);
            PadraoSistema.listaEnfermeiros.add(y);
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
            if (txtCoren.getText().isEmpty()) {
                throw new Exception("Escreva o Coren do enfermeiro!");
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
