package br.edu.fesa.controller;

import br.edu.fesa.model.Convenio;
import br.edu.fesa.model.FichaMedica;
import br.edu.fesa.model.Paciente;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

public class AtendimentoController implements Initializable {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnNovoPaciente;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnCarregar;

    @FXML
    private TableColumn<Paciente, String> clCpf;

    @FXML
    private TableColumn<Paciente, LocalDate> clDataNasc;

    @FXML
    private TableColumn<Paciente, Integer> clId;

    @FXML
    private TableColumn<Paciente, Integer> clIdConvenio;

    @FXML
    private TableColumn<Paciente, String> clNome;

    @FXML
    private TableView<Paciente> tbPacientes;

    @FXML
    private TextField txtBusca;

    private ObservableList<Paciente> observablePaciente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criaTabela();
        carregaPacientes();
        filtraPacientes();
    }
    
    public void criaTabela() {
        clId.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        clNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        clDataNasc.setCellValueFactory(
                new PropertyValueFactory<>("dataNasc"));
        clCpf.setCellValueFactory(
                new PropertyValueFactory<>("cpf"));
        clIdConvenio.setCellValueFactory(
                new PropertyValueFactory<>("idConvenio"));
    }

    public void carregaPacientes() {
        tbPacientes.getItems().removeAll();
        observablePaciente = FXCollections.observableArrayList(PadraoSistema.listPacientes);

        tbPacientes.setItems(observablePaciente);
    }
    
    public void filtraPacientes() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Paciente> filteredData = new FilteredList<>(observablePaciente, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txtBusca.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(paciente -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (paciente.getCpf().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches CPF.
                } else if (paciente.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches name.
                } else if (String.valueOf(paciente.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches id.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Paciente> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tbPacientes.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbPacientes.setItems(sortedData);
    }

    public void novoPaciente() {
        try {
            App.setRoot("FormularioNovoPaciente");
        } catch (IOException e) {
            PadraoSistema.exibeErro("Não foi possivel abrir a tela de NOVO PACIENTE.");
        }
    }

    public void confirmarRemover() {
        Paciente paciente = tbPacientes.getSelectionModel().getSelectedItem();
        if (paciente != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletar");
            alert.setHeaderText("Deseja deletar o usuário?");
            alert.setContentText(paciente.toString());
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    PadraoSistema.listPacientes.remove(paciente);
                    carregaPacientes();
                }
            });
        } else {
            PadraoSistema.exibeErro("Selecione um paciente!");
        }
    }

    public void editarPaciente() {
        try {
            if (tbPacientes.getSelectionModel().getSelectedItem() != null) {
                FormularioEditarPacienteController.paciente = tbPacientes.getSelectionModel().getSelectedItem();
                App.setRoot("FormularioEditarPaciente");
            } else {
                PadraoSistema.exibeErro("Selecione um paciente!");
            }

        } catch (IOException e) {
            PadraoSistema.exibeErro("Não foi possivel abrir a tela de NOVO PACIENTE.");
        }
    }

    public void criarFicharMedica() {

        Paciente paciente = tbPacientes.getSelectionModel().getSelectedItem();
        if (paciente != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "",
                    ButtonType.YES, ButtonType.NO);
            alert.setTitle("Ficha médica");
            alert.setHeaderText("Deseja criar uma ficha médica para o usuário: ");
            alert.setContentText(paciente.toString());
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.YES) {

                    FichaMedica ficha = new FichaMedica(paciente.getId(),
                            PadraoSistema.usuarioLogado.getId(),
                            paciente.getNome(), paciente.getDataNasc());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    ficha.setHorarioAtendimento(LocalTime.parse(LocalTime.now().format(formatter)));
                    ficha.setDiaAtendimento(LocalDate.now());
                    PadraoSistema.listFichasMedica.add(ficha);
                    PadraoSistema.exibeSucesso("Ficha médica criada para o paciente: "
                            + paciente.toString());

                }
            });
        } else {
            PadraoSistema.exibeErro("Selecione um paciente!");
        }
    }

    public void confirmarSaida() {
        PadraoSistema.confirmarSaida();
    }

}
