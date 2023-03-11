package br.edu.fesa.controller;

import br.edu.fesa.model.FichaMedica;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class ConsultaController implements Initializable {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnCarregar;

    @FXML
    private TextField txtBusca;

    @FXML
    private TableColumn<FichaMedica, LocalDate> clDataNasc;

    @FXML
    private TableColumn<FichaMedica, Integer> clId;

    @FXML
    private TableColumn<FichaMedica, LocalTime> clHorarioAtendimento;

    @FXML
    private TableColumn<FichaMedica, String> clNome;

    @FXML
    private TableColumn<FichaMedica, String> clClassificacao;

    @FXML
    private TableView<FichaMedica> tbFichaMedica;

    private ObservableList<FichaMedica> observableFichaMedica;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        criaTabela();
        carregaFichaMedica();
        filtraFicha();
    }

    @FXML
    public void criaTabela() {
        clId.setCellValueFactory(
                new PropertyValueFactory<>("idPaciente"));
        clNome.setCellValueFactory(
                new PropertyValueFactory<>("nomePaciente"));
        clDataNasc.setCellValueFactory(
                new PropertyValueFactory<>("dataNasc"));
        clClassificacao.setCellValueFactory(
                new PropertyValueFactory<>("classificacao"));
        clHorarioAtendimento.setCellValueFactory(
                new PropertyValueFactory<>("horarioAtendimento"));
        //clIdConvenio.setCellValueFactory(
        //        new PropertyValueFactory<>("idConvenio"));        
    }

    public void carregaFichaMedica() {
        tbFichaMedica.getItems().removeAll();
        observableFichaMedica = FXCollections.observableArrayList(separaConsulta());
        tbFichaMedica.setItems(observableFichaMedica);
    }

    public List<FichaMedica> separaConsulta() {

        List<FichaMedica> lista = new ArrayList();
        for (FichaMedica x : PadraoSistema.listFichasMedica) {
            if ((x.getDiagnostico() == null) && (x.getClassificacao() != null) ) {
                lista.add(x);
            }
        }
        return lista;
    }

    public void filtraFicha() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<FichaMedica> filteredData = new FilteredList<>(observableFichaMedica, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txtBusca.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ficha -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (ficha.getNomePaciente().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches name.
                } else if (String.valueOf(ficha.getIdPaciente()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches id.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<FichaMedica> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tbFichaMedica.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbFichaMedica.setItems(sortedData);
    }

    @FXML
    public void cliqueEditarFichaMedica() {
        try {
            if (tbFichaMedica.getSelectionModel().getSelectedItem() != null) {
                FormularioFichaMedicaController.ficha = tbFichaMedica.getSelectionModel().getSelectedItem();
                App.setRoot("FormularioFichaMedica");
            } else {
                PadraoSistema.exibeErro("Selecione um paciente!");
            }

        } catch (IOException e) {
            PadraoSistema.exibeErro("Não foi possivel abrir a tela de EDITAR CONSULTA MEDICA.");
        }
    }

    @FXML
    public void avisoRemover() {
        FichaMedica ficha = tbFichaMedica.getSelectionModel().getSelectedItem();
        if (ficha != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletar");
            alert.setHeaderText("Deseja deletar a ficha medica?");
            alert.setContentText(ficha.toString());
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    PadraoSistema.listFichasMedica.remove(ficha);
                    carregaFichaMedica();
                }
            });
        } else {
            PadraoSistema.exibeErro("Selecione uma ficha medica!");
        }
    }
    
    public void confirmarSaida() {
        PadraoSistema.confirmarSaida();
    }

}
