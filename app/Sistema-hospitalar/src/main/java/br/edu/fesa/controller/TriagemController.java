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

public class TriagemController implements Initializable {

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnEditar;
    
    @FXML
    private Button btnSair;

    @FXML
    private TableColumn<FichaMedica, LocalDate> clDataNasc;

    @FXML
    private TableColumn<FichaMedica, Integer> clId;

    @FXML
    private TableColumn<FichaMedica, LocalTime> clTempoEspera;

    @FXML
    private TableColumn<FichaMedica, String> clNome;

    @FXML
    private TableView<FichaMedica> tbFichaMedica;

    @FXML
    private TextField txtBusca;

    private ObservableList<FichaMedica> observableFichaMedica;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        criaTabela();
        carregarFichaMedica();
        filtraFicha();
    }

    public void carregarFichaMedica() {
        tbFichaMedica.getItems().removeAll();        
        observableFichaMedica = FXCollections.observableArrayList(separarTriagem());
        tbFichaMedica.setItems(observableFichaMedica);
    }

    public void criaTabela() {
        clId.setCellValueFactory(
                new PropertyValueFactory<>("idPaciente"));
        clNome.setCellValueFactory(
                new PropertyValueFactory<>("nomePaciente"));
        clDataNasc.setCellValueFactory(
                new PropertyValueFactory<>("dataNasc"));
        clTempoEspera.setCellValueFactory(
                new PropertyValueFactory<>("horarioAtendimento"));
        //clIdConvenio.setCellValueFactory(
        //        new PropertyValueFactory<>("idConvenio"));
    }
    
    public void filtraFicha(){
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
    
    public List<FichaMedica> separarTriagem(){
        
        List<FichaMedica> lista = new ArrayList();
        for(FichaMedica x : PadraoSistema.listFichasMedica)
        {
            if(x.getClassificacao() == null){
                lista.add(x);
            }
        }
        return lista;
    }

    public void editarTriagem() {
        try {
            if (tbFichaMedica.getSelectionModel().getSelectedItem() != null) {
                FormularioTriagemController.ficha = tbFichaMedica.getSelectionModel().getSelectedItem();
                App.setRoot("FormularioTriagem");
            } else {
                PadraoSistema.exibeErro("Selecione um paciente!");
            }

        } catch (IOException e) {
            PadraoSistema.exibeErro("Não foi possivel abrir a tela de FORMULARIO TRIAGEM.");
        }
    }

    public void confirmarRemover() {
        FichaMedica ficha = tbFichaMedica.getSelectionModel().getSelectedItem();
        if (ficha != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletar");
            alert.setHeaderText("Deseja deletar a ficha medica?");
            alert.setContentText(ficha.toString());
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    PadraoSistema.listFichasMedica.remove(ficha);
                    carregarFichaMedica();
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
