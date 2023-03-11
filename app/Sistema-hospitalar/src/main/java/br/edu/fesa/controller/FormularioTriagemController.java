package br.edu.fesa.controller;

import br.edu.fesa.model.FichaMedica;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class FormularioTriagemController implements Initializable {

    @FXML
    private Button btnEditarFichaMedica;

    @FXML
    private Button btnVoltar;

    @FXML
    private ComboBox<String> cbClassificacao;

    @FXML
    private DatePicker dpDataNasc;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPressao;

    @FXML
    private TextField txtTemperatura;

    private List<String> classificacoes = new ArrayList();
    public static FichaMedica ficha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaCbClassificacao();
        escreveFichaMedica();

    }

    public void carregaCbClassificacao() {
        classificacoes.add("Verde");
        classificacoes.add("Amarelo");
        classificacoes.add("Vermelho");

        cbClassificacao.setItems(FXCollections.observableArrayList(classificacoes));
    }

    public void escreveFichaMedica() {
        FichaMedica x = ficha;
        txtNome.setText(x.getNomePaciente());
        dpDataNasc.setValue(x.getDataNasc());
    }

    @FXML
    public void salvarFichaTriagem() {
        if (valida()) {
            PadraoSistema.listFichasMedica.remove(ficha);
            FichaMedica x = ficha;
            x.setTemperatura(Integer.parseInt(txtTemperatura.getText()));
            x.setClassificacao(cbClassificacao.getSelectionModel().getSelectedItem());
            x.setPressao(txtPressao.getText());
            x.setIdEnfermeiro(PadraoSistema.usuarioLogado.getId());
            PadraoSistema.listFichasMedica.add(x);
            PadraoSistema.exibeSucesso(x.toString());
            voltarTela();
        }

    }

    public Boolean valida() {

        Boolean validacao = false;

        try {

            if (cbClassificacao.getSelectionModel().getSelectedItem() == null) {
                throw new Exception("Selecione uma Classificação!");
            }
            if (txtNome.getText().isEmpty() || txtPressao.getText().isBlank()) {
                throw new Exception("Não há nome preenchido!");
            }
            if (dpDataNasc.getValue() == null || dpDataNasc.getValue().isAfter(LocalDate.now())) {
                throw new Exception("Insira uma data válida!");
            }
            if (txtPressao.getText().isEmpty() || txtPressao.getText().isBlank()) {
                throw new Exception("Insira a Pressão do Paciente!");
            }
            if (txtTemperatura.getText().isEmpty() || txtTemperatura.getText().isBlank() || Integer.parseInt(txtTemperatura.getText()) <= 20) {
                throw new Exception("Insira uma Temperatura valida do Paciente!");
            }
            validacao = true;
        } catch (Exception e) {
            PadraoSistema.exibeErro(e.getMessage());
        } finally {
            return validacao;
        }
    }

    public void voltarTela() {
        PadraoSistema.irParaTela("Triagem");
    }
}
