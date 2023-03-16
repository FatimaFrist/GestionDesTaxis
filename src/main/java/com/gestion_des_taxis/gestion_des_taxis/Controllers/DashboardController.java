package com.gestion_des_taxis.gestion_des_taxis.Controllers;

import com.gestion_des_taxis.gestion_des_taxis.Models.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label nbrVoiture;

    @FXML
    private Label nbrReparation;

    @FXML
    private Label nbrChauffeur;

    @FXML
    private Label nbrDemande;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label nbrClient;

    @FXML
    private Label vidange1;

    @FXML
    private Label vidange2;

    @FXML
    private Label vidange3;

    @FXML
    private Label vidange4;

    @FXML
    private Label demand1;

    @FXML
    private Label demand2;

    @FXML
    private Label demand3;

    @FXML
    private Label demand4;

    String Query = null;
    Connection Con = null;
    PreparedStatement PreStatment = null;
    ResultSet resultSet = null;

    public int getNumberVoiturs() {
        int Count = 0;
        try {
            Con = DataBaseConnection.GetConnection();
            Query = "SELECT Count(*) FROM `voiture` ";
            PreStatment = Con.prepareStatement(Query);
            resultSet = PreStatment.executeQuery();
            resultSet.next();
            Count = resultSet.getInt(1);
        } catch (SQLException ex) {
        }
        return Count;
    }

    public int getNumberReparation() {
        int Count = 0;
        try {
            Con = DataBaseConnection.GetConnection();
            Query = "SELECT Count(*) FROM `vidange` ";
            PreStatment = Con.prepareStatement(Query);
            resultSet = PreStatment.executeQuery();
            resultSet.next();
            Count = resultSet.getInt(1);
            Query = "SELECT Count(*) FROM `reparation` ";
            PreStatment = Con.prepareStatement(Query);
            resultSet = PreStatment.executeQuery();
            resultSet.next();
            Count += resultSet.getInt(1);
        } catch (SQLException ex) {
        }
        return Count;
    }

    public int getNumberChauffeurs() {
        int Count = 0;
        try {
            Con = DataBaseConnection.GetConnection();
            Query = "SELECT Count(*) FROM `chauffeur` ";
            PreStatment = Con.prepareStatement(Query);
            resultSet = PreStatment.executeQuery();
            resultSet.next();
            Count = resultSet.getInt(1);
        } catch (SQLException ex) {
        }
        return Count;
    }

    public int getNumberDemandes() {
        int Count = 0;
        try {
            Con = DataBaseConnection.GetConnection();
            Query = "SELECT Count(*) FROM `demande`";
            PreStatment = Con.prepareStatement(Query);
            resultSet = PreStatment.executeQuery();
            resultSet.next();
            Count = resultSet.getInt(1);
        } catch (SQLException ex) {
        }
        return Count;
    }

    public int getNumberClients() {
        int Count = 0;
        try {
            Con = DataBaseConnection.GetConnection();
            Query = "SELECT Count(*) FROM `client` ";
            PreStatment = Con.prepareStatement(Query);
            resultSet = PreStatment.executeQuery();
            resultSet.next();
            Count = resultSet.getInt(1);
        } catch (SQLException ex) {
        }
        return Count;
    }

    public void FillPieChar() {
        int Total = 0;
        try {
            Con = DataBaseConnection.GetConnection();
            Query = "SELECT COUNT(*) FROM demande ";
            PreStatment = Con.prepareStatement(Query);
            resultSet = PreStatment.executeQuery();
            resultSet.next();
            if (resultSet.getInt(1) == 0) {
                Total = 1;
            } else {
                Total = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("En attente " + (CountStatus("En attente") * 100) / Total + "%", CountStatus("En attente")),
                new PieChart.Data("Annulé " + (CountStatus("Annulé") * 100) / Total + "%", CountStatus("Annulé")),
                new PieChart.Data("Accepté " + (CountStatus("Accepté") * 100) / Total + "%", CountStatus("Accepté")),
                new PieChart.Data("Refusé " + (CountStatus("Refusé") * 100) / Total + "%", CountStatus("Refusé"))
        );
        pieChart.setData(pieChartData);
    }

    public int CountStatus(String Status) {
        int count = 0;
        try {
            Con = DataBaseConnection.GetConnection();
            Query = "SELECT COUNT(*) FROM demande WHERE Status = '" + Status + "'";
            PreStatment = Con.prepareStatement(Query);
            resultSet = PreStatment.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);

        } catch (SQLException ex) {
        }
        return count;
    }

    public void FillLineChar() {
        int[] Counts = new int[7];
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("u");
        Date date = new Date();
        int dayNbr = Integer.parseInt(formatter2.format(date));
        System.out.println("day number " + dayNbr);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -dayNbr + 1);
        Date ReturnDate = null;
        for (int i = 0; i < 7; i++) {
            c.add(Calendar.DATE, - 1);
            ReturnDate = c.getTime();
            System.out.println("date " + formatter.format(ReturnDate));
            try {
                Con = DataBaseConnection.GetConnection();
                Query = "SELECT COUNT(*) FROM demande WHERE date_demande = '" + formatter.format(ReturnDate) + "'";
                PreStatment = Con.prepareStatement(Query);
                resultSet = PreStatment.executeQuery();
                resultSet.next();
                Counts[i] = resultSet.getInt(1);
            } catch (SQLException ex) {
            }
        }
        int[] Counts2 = new int[7];
        Calendar c2 = Calendar.getInstance();
        c2.setTime(ReturnDate);
        c2.add(Calendar.DATE, -7);
        System.out.println("two week " + formatter.format(c2.getTime()));
        for (int i = 0; i < 7; i++) {
            c2.add(Calendar.DATE, - 1);
            Date ReturnDate2 = c2.getTime();
            System.out.println("date " + formatter.format(ReturnDate2));
            try {
                Con = DataBaseConnection.GetConnection();
                Query = "SELECT COUNT(*) FROM demande WHERE date_demande = '" + formatter.format(ReturnDate2) + "'";
                PreStatment = Con.prepareStatement(Query);
                resultSet = PreStatment.executeQuery();
                resultSet.next();
                Counts2[i] = resultSet.getInt(1);
            } catch (SQLException ex) {
            }
        }
        for (int i = 0; i < 7; i++) {
            System.out.println("count1 " + Counts[i]);
        }
        for (int i = 0; i < 7; i++) {
            System.out.println("count2 " + Counts2[i]);
        }

        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Monday", Counts[6]));
        series.getData().add(new XYChart.Data("Tuesday", Counts[5]));
        series.getData().add(new XYChart.Data("Wednesday", Counts[4]));
        series.getData().add(new XYChart.Data("Thursday", Counts[3]));
        series.getData().add(new XYChart.Data("Friday", Counts[2]));
        series.getData().add(new XYChart.Data("Saturday", Counts[1]));
        series.getData().add(new XYChart.Data("Sunday", Counts[0]));
        series.setName("A week ago");

        XYChart.Series series2 = new XYChart.Series();
        series2.getData().add(new XYChart.Data("Monday", Counts2[6]));
        series2.getData().add(new XYChart.Data("Tuesday", Counts2[5]));
        series2.getData().add(new XYChart.Data("Wednesday", Counts2[4]));
        series2.getData().add(new XYChart.Data("Thursday", Counts2[3]));
        series2.getData().add(new XYChart.Data("Friday", Counts2[2]));
        series2.getData().add(new XYChart.Data("Saturday", Counts2[1]));
        series2.getData().add(new XYChart.Data("Sunday", Counts2[0]));
        series2.setName("Two weeks ago");

        lineChart.getData().addAll(series, series2);
        series.getNode().setStyle("-fx-stroke: #0074D1; -fx-stroke-width: 4px;");
        series2.getNode().setStyle("-fx-stroke: #51C9C2; -fx-stroke-width: 4px;");
    }

    public void getDernierDemandes() {
        String[] demandes = new String[4];
        try {
            Con = DataBaseConnection.GetConnection();
            Query = "SELECT `voiture`, `client`, `chauffeur` FROM `demande` ORDER by date_demande DESC LIMIT 4";
            PreStatment = Con.prepareStatement(Query);
            resultSet = PreStatment.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                demandes[i] = resultSet.getString(1) + "-" + resultSet.getString(2) + "-" + resultSet.getString(3);
                i++;
            }
        } catch (SQLException ex) {
        }

        demand1.setText(demandes[0]);
        demand2.setText(demandes[1]);
        demand3.setText(demandes[2]);
        demand4.setText(demandes[3]);

    }

    public void getDernierReparations() {
        String[] reparation = new String[4];
        try {
            Con = DataBaseConnection.GetConnection();
            Query = "SELECT `immatriculation`, `designation`, `prixTTC` FROM `reparation` ORDER by dateReparation DESC LIMIT 4";
            PreStatment = Con.prepareStatement(Query);
            resultSet = PreStatment.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                reparation[i] = resultSet.getString(1) + "-" + resultSet.getString(2) + "-" + resultSet.getString(3);
                i++;
            }
        } catch (SQLException ex) {
        }

        vidange1.setText(reparation[0]);
        vidange2.setText(reparation[1]);
        vidange3.setText(reparation[2]);
        vidange4.setText(reparation[3]);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nbrChauffeur.setText(String.valueOf(getNumberChauffeurs()));
        nbrClient.setText(String.valueOf(getNumberClients()));
        nbrDemande.setText(String.valueOf(getNumberDemandes()));
        nbrReparation.setText(String.valueOf(getNumberReparation()));
        nbrVoiture.setText(String.valueOf(getNumberVoiturs()));
        FillPieChar();
        FillLineChar();
        getDernierDemandes();
        getDernierReparations();
    }
}
