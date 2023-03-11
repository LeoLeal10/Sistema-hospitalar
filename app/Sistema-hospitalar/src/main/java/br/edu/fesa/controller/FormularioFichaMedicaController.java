package br.edu.fesa.controller;

import br.edu.fesa.model.FichaMedica;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FormularioFichaMedicaController implements Initializable {


    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnVoltar;

    @FXML
    private DatePicker dpDataNasc;

    @FXML
    private TextField txtClassificacao;

    @FXML
    private TextArea txtDiagnostico;

    @FXML
    private TextField txtId;

    @FXML
    private TextArea txtMedicacao;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPressao;

    @FXML
    private TextField txtTemperatura;
    
    public static FichaMedica ficha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        escreveFichaMedica();
        
    }    
    
    public void escreveFichaMedica() {
        txtNome.setText(ficha.getNomePaciente());
        dpDataNasc.setValue(ficha.getDataNasc());
        txtId.setText(String.valueOf(ficha.getId()));
        txtClassificacao.setText(ficha.getClassificacao());
        txtPressao.setText(ficha.getPressao());
        txtTemperatura.setText(String.valueOf(ficha.getTemperatura()));
    }
    
    public void salvarFichaMedica() {
        if (valida()) {
            PadraoSistema.listFichasMedica.remove(ficha);
            FichaMedica x = ficha;
            x.setDiagnostico(txtDiagnostico.getText());
            x.setMedicacao(txtMedicacao.getText());
            x.setIdMedico(PadraoSistema.usuarioLogado.getId());
            PadraoSistema.listFichasMedica.add(x);
            PadraoSistema.exibeSucesso(x.toString());
            voltarTela();
        }

    }

    public Boolean valida() {

        Boolean validacao = false;

        try {
            if (txtDiagnostico.getText().isEmpty() || txtDiagnostico.getText().isBlank()) {
                throw new Exception("Insira o Diagnostico do Paciente!");
            }
            if (txtMedicacao.getText().isEmpty() || txtMedicacao.getText().isBlank() || Integer.parseInt(txtTemperatura.getText()) <= 20) {
                throw new Exception("Insira as Medicações do Paciente!");
            }
            validacao = true;
        } catch (Exception e) {
            PadraoSistema.exibeErro(e.getMessage());
        } finally {
            return validacao;
        }
    }

    public void voltarTela() {
        PadraoSistema.irParaTela("Consulta");
    }

    
    
}
