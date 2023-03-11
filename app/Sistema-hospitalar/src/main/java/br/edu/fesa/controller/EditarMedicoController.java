/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.controller;

import static br.edu.fesa.controller.PadraoSistema.listaMedicos;
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

/**
 * FXML Controller class
 *
 * @author sllui
 */
public class EditarMedicoController implements Initializable {

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
    
    public static Usuario user;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        escreveMedico();
        carregaCbEspecialidade();
    }

    public void voltarTela() {
        PadraoSistema.irParaTela("ListaFuncionarios");
    }
    
    public void escreveMedico() {
        txtNome.setText(user.getNome());
        dpDataNasc.setValue(user.getDataNasc());
        txtLogin.setText(user.getLogin());
        txtSenha.setText(user.getSenha());
        for (Medico x : listaMedicos) {
            if (user.getIdUser() == x.getId()) {
                txtCrm.setText(x.getCrm());
                cbEspecialidade.setValue(x.getEspecialidade());
            }
        }
    }
    
    public void carregaCbEspecialidade() {
        cbEspecialidade.getItems().removeAll();
        cbEspecialidade.setItems(FXCollections.observableArrayList(PadraoSistema.listaEspecialidades));        
    }
    
    public void salvaMedico() {
        if(valida()) {   
            Usuario x  = new Usuario(user.getId(), txtNome.getText(),
                    dpDataNasc.getValue(),txtLogin.getText(),
                    txtSenha.getText(), "Médico",
                    user.getIdUser());
            Medico y = new Medico(txtCrm.getText(), cbEspecialidade.getSelectionModel().getSelectedItem(), user.getIdUser());
            PadraoSistema.listaUsers.remove(user);
            for (Medico medicoUser : listaMedicos) {
                if (user.getIdUser() == medicoUser.getId()) {
                    PadraoSistema.listaMedicos.remove(medicoUser);
                }
            }
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
