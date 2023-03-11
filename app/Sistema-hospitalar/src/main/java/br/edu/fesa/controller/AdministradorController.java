package br.edu.fesa.controller;

import br.edu.fesa.model.FichaMedica;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class AdministradorController implements Initializable {

    @FXML
    private BorderPane bpAtendimentoSemana;
    
    @FXML
    private BorderPane bpClassificacao;

    @FXML
    private Button btnListaFunc;

    @FXML
    private Button btnSair;

    @FXML
    private Label lbQtdAtendidas;

    @FXML
    private Label lbQtdEspera;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaDadosAtendimentoMes();
        carregaDadosAtendimentoSemana();
        carregaDadosClassificacao();
    }

    public void listarFuncionarios() {
        PadraoSistema.irParaTela("ListaFuncionarios");
    }

    public void confirmarSaida() {
        PadraoSistema.confirmarSaida();
    }

    public void carregaDadosAtendimentoMes() {
        int qtdEspera = 0;
        int qtdAtendidas = 0;

        for (FichaMedica ficha : PadraoSistema.listFichasMedica) {
            if ((ficha.getDiagnostico() == null) && (ficha.getClassificacao() == null)) {
                qtdEspera++;
            } 
            if (ficha.getMedicacao() != null && LocalDate.now().getMonth()
                    .equals(ficha.getDiaAtendimento().getMonth())) {
                qtdAtendidas++;
            }
        }

        lbQtdEspera.setText(Integer.toString(qtdEspera));
        lbQtdAtendidas.setText(Integer.toString(qtdAtendidas));
    }

    public void carregaDadosAtendimentoSemana() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Dia semana");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Quantidade");
        
        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series data = new XYChart.Series();
        data.setName("Atendimentos da semana");
        
        for (int x = -6; x <= 0; x++) {
            LocalDate date = LocalDate.now().plusDays(x);
            data.getData().add(new XYChart.Data(date.toString(),qtdFichaMedica(date)));
        }
        
        barChart.getData().add(data);
        
        bpAtendimentoSemana.setCenter(barChart);        
    }
    
    public void carregaDadosClassificacao() {
        ObservableList<PieChart.Data> pieChartdata = FXCollections.observableArrayList(
            new PieChart.Data("Vermelho", qtdClassificacao("Vermelho")),
            new PieChart.Data("Amarelo", qtdClassificacao("Amarelo")),
            new PieChart.Data("Verde", qtdClassificacao("Verde"))
        );
        
        PieChart pieChart = new PieChart(pieChartdata);
        pieChart.setTitle("Classificações");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(0);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);
        
        bpClassificacao.setCenter(pieChart);
    }
    
    public int qtdFichaMedica(LocalDate data) {
        int qtd = 0;
        
        for (FichaMedica ficha : PadraoSistema.listFichasMedica) {
            if (data.isEqual(ficha.getDiaAtendimento()))
                qtd++;
        }
        
        return qtd;
    }
    
    public int qtdClassificacao(String classificacao) {
        int qtd = 0;
        
        for (FichaMedica ficha : PadraoSistema.listFichasMedica) {
            if (classificacao.equals(ficha.getClassificacao()))
                qtd++;
        }
        
        return qtd;        
    }

}
