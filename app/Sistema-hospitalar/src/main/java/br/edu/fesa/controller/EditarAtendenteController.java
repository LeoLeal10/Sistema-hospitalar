/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.controller;

import static br.edu.fesa.controller.PadraoSistema.listaAtendentes;
import br.edu.fesa.model.Atendente;
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
public class EditarAtendenteController implements Initializable {

   @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @FXML
    private DatePicker dpDataNasc;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtSenhaConfirm;
    
    public static Usuario user;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaEnfermeiro();
    }
    
    public void carregaEnfermeiro(){
        txtNome.setText(user.getNome());
        dpDataNasc.setValue(user.getDataNasc());
        txtLogin.setText(user.getLogin());
        txtSenha.setText(user.getSenha());
    }
    
    public void salvarAtendente() {
        if(valida()) {   
            Usuario x  = new Usuario(user.getId(), txtNome.getText(),
                    dpDataNasc.getValue(),txtLogin.getText(),
                    txtSenha.getText(), "Atendente",
                    user.getIdUser());
            Atendente y = new Atendente(user.getIdUser());
            for (Atendente userAtendente : listaAtendentes) {
                if (user.getIdUser() == userAtendente.getId()) {
                    PadraoSistema.listaAtendentes.remove(userAtendente);
                    PadraoSistema.listaUsers.remove(user);
                }
            }
            PadraoSistema.listaUsers.add(x);
            PadraoSistema.listaAtendentes.add(y);
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
