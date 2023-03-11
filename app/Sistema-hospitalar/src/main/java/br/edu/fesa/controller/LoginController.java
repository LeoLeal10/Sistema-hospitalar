package br.edu.fesa.controller;

import br.edu.fesa.model.Administrador;
import br.edu.fesa.model.Atendente;
import br.edu.fesa.model.Enfermeiro;
import br.edu.fesa.model.Medico;
import br.edu.fesa.model.Paciente;
import br.edu.fesa.model.Usuario;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

    @FXML
    private Button btnEntrar;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtSenha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void realizarLogin() {

        try {

            Usuario user = new Usuario(txtLogin.getText(), txtSenha.getText());
 
            if (user.getSenha().isEmpty() || user.getLogin().isEmpty()) {
                throw new Exception("Preencha Login/Senha!");
            }

            for (Usuario u : PadraoSistema.listaUsers) {
                if (user.getLogin().equals(u.getLogin())
                        && user.getSenha().equals(u.getSenha())) {
                    PadraoSistema.usuarioLogado = user;
                    String tela = "";
                    switch (u.getTipoUser()) {
                        case "Atendente":
                            tela = "Atendimento";
                            break;
                        case "Médico":
                            tela = "Consulta";
                            break;
                        case "Enfermeiro":
                            tela = "Triagem";
                            break;
                        case "Administrador":
                            tela = "Administrador";
                            break;
                        default:
                            PadraoSistema.exibeErro("Erro ao realizar login no sistema!");
                            break;
                    }
                    if (!tela.isEmpty()) {
                        App.setRoot(tela);
                    }
                    return;
                }
            }

            throw new Exception("Login/Senha inválido!");

        } catch (Exception e) {
            PadraoSistema.exibeErro(e.getMessage());
            System.out.println(e.getMessage());
        }

    }

}
