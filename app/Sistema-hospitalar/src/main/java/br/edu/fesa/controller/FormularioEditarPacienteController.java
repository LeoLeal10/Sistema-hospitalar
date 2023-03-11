package br.edu.fesa.controller;

import br.edu.fesa.model.Convenio;
import br.edu.fesa.model.Paciente;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class FormularioEditarPacienteController implements Initializable {

    @FXML
    private Button btnCriarPaciente;

    @FXML
    private ComboBox<Convenio> cbConvenio;

    @FXML
    private DatePicker dpDataNasc;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtCep;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtNome;

    private ObservableList<Convenio> obsConvenios;
    public static Paciente paciente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaCbConvenio();
        escrevePaciente();
    }

    public void carregaCbConvenio() {
        cbConvenio.setItems(FXCollections.observableArrayList(PadraoSistema.listConvenios));
    }

    public void editarPaciente() {
        if (valida()) {
            Paciente x = new Paciente(0, txtNome.getText(),
                    dpDataNasc.getValue(), txtCpf.getText(),
                    cbConvenio.getSelectionModel().getSelectedItem().getId(),
                    txtCep.getText(), txtEndereco.getText(),
                    Integer.parseInt(txtNumero.getText()),
                    txtTelefone.getText());
            PadraoSistema.listPacientes.remove(paciente);
            PadraoSistema.listPacientes.add(x);
            PadraoSistema.exibeSucesso(x.toString());
            voltarTela();
        }

    }

    public void escrevePaciente() {
        Paciente x = FormularioEditarPacienteController.paciente;
        txtNome.setText(x.getNome());
        dpDataNasc.setValue(x.getDataNasc());
        txtCpf.setText(x.getCpf());
        txtCep.setText(x.getCep());
        txtEndereco.setText(x.getEndereco());
        txtTelefone.setText(x.getTelefone());
        cbConvenio.getSelectionModel().select(x.getIdConvenio());
        txtNumero.setText(Integer.toString(x.getNumero()));

    }

    public Boolean valida() {

        Boolean validacao = false;

        try {

            if (cbConvenio.getSelectionModel().getSelectedItem() == null) {
                throw new Exception("Selecione um Convênio!");
            }
            if (txtNome.getText().isEmpty()) {
                throw new Exception("Não há nome preenchido!");
            }
            if (txtEndereco.getText().isEmpty()) {
                throw new Exception("Não há endereço preenchido!");
            }
            if (txtCpf.getText().isEmpty()) {
                throw new Exception("Não há CPF preenchido!");
            }
            if (txtCep.getText().isEmpty()) {
                throw new Exception("Não há CEP preenchido!");
            }
            if (txtTelefone.getText().isEmpty()) {
                throw new Exception("Não há telefone preenchido!");
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
        PadraoSistema.irParaTela("Atendimento");
    }

}
