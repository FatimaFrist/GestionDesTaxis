package com.gestion_des_taxis.gestion_des_taxis.Controllers;

import com.gestion_des_taxis.gestion_des_taxis.Models.DataBaseConnection;
import com.gestion_des_taxis.gestion_des_taxis.Models.Tools;
import com.gestion_des_taxis.gestion_des_taxis.Models.Voiture;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.ResourceBundle;

public class AddVoitureController implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label pageTitle;

    @FXML
    private TextField immaVal;

    @FXML
    private TextField modelVal;

    @FXML
    private TextField marqueVal;

    @FXML
    private TextField carbuVal;

    @FXML
    private ComboBox<String> etatVal;

    @FXML
    private TextField consomation_moyenne_val;

    @FXML
    private TextField puissance_fiscale_val;

    @FXML
    private TextField killometrage_val;

    @FXML
    private MFXDatePicker date_1er_immatru_val;

    @FXML
    private MFXDatePicker date_dernier_controle_tech_val;

    @FXML
    private TextField km_dernirer_vidange_val;

    @FXML
    private TextField km_dernier_courroie_val;

    @FXML
    private Label errorMessage;

    @FXML
    private MFXButton ajouterBtn;

    @FXML
    private MFXButton modifierBtn;


    private String query = null;

    private Connection con = null;

    private PreparedStatement PpeStatment = null;

    private ResultSet resultSet = null;
    private Voiture voiture = null;

    public static int voitureId;


    @FXML
    void ajouter(MouseEvent event) throws Exception {
        con = DataBaseConnection.GetConnection();
        String immatriculation = immaVal.getText();
        String model = modelVal.getText();
        String marque = marqueVal.getText();
        String carburant = carbuVal.getText();
        String etat = etatVal.getSelectionModel().getSelectedItem();
        String consomation_moyenne = consomation_moyenne_val.getText();
        String puissance_fiscale = puissance_fiscale_val.getText();
        String date_1er_immatru = date_1er_immatru_val.getValue() + "";
        String killometrage = killometrage_val.getText();
        String date_dernier_controle_tech = date_dernier_controle_tech_val.getValue() + "";
        String km_dernirer_vidange = km_dernirer_vidange_val.getText();
        String km_dernier_courroie = km_dernier_courroie_val.getText();

        System.out.println(immatriculation);
        System.out.println(marque);
        System.out.println(model);
        System.out.println(carburant);
        System.out.println(etat);
        System.out.println(consomation_moyenne);
        System.out.println(puissance_fiscale);
        System.out.println(date_1er_immatru);
        System.out.println(killometrage);
        System.out.println(km_dernirer_vidange);
        System.out.println(date_dernier_controle_tech);
        System.out.println(km_dernier_courroie);
        //  System.out.println(immatriculation.isEmpty() || model.isEmpty() || marque.isEmpty() || carburant.isEmpty() || etat.isEmpty());
        if (immatriculation.isEmpty() || model.isEmpty() || marque.isEmpty() || carburant.isEmpty() || etat.isEmpty()) {
            if (immatriculation.isEmpty()) {
                immaVal.setStyle("-fx-border-color: red;");
                new animatefx.animation.Shake(immaVal).play();
                errorMessage.setVisible(true);
                errorMessage.setText("L'immatriculation est obligatoire!");
                new animatefx.animation.BounceIn(errorMessage).play();
            } else {
                immaVal.setStyle("-fx-border-color: white;");
            }
            if (model.isEmpty()) {
                modelVal.setStyle("-fx-border-color: red;");
                new animatefx.animation.Shake(modelVal).play();
                errorMessage.setVisible(true);
                errorMessage.setText("Le model est obligatoire!");
                new animatefx.animation.BounceIn(errorMessage).play();
            } else {
                modelVal.setStyle("-fx-border-color: white;");
            }
            if (marque.isEmpty()) {
                marqueVal.setStyle("-fx-border-color: red;");
                new animatefx.animation.Shake(marqueVal).play();
                errorMessage.setVisible(true);
                errorMessage.setText("La marque est obligatoire!");
                new animatefx.animation.BounceIn(errorMessage).play();
            } else {
                marqueVal.setStyle("-fx-border-color: white;");
            }

            if (carburant.isEmpty()) {
                carbuVal.setStyle("-fx-border-color: red;");
                new animatefx.animation.Shake(carbuVal).play();
                errorMessage.setVisible(true);
                errorMessage.setText("Le carburant est obligatoire!");
                new animatefx.animation.BounceIn(errorMessage).play();
            }
        } else {
            carbuVal.setStyle("-fx-border-color: white;");

            System.out.println("test else");
            String username = immatriculation + "_" + marque + "_" + new Random(9);
            //           String password = Tools.generatePassword();
            voiture = new Voiture();
            int immaCount = 0;
            ///Test CNE And Moblie if are already exist
            System.out.println("test if isedit");
            Tools T = new Tools();
            immaCount = T.testColumn("voiture", "immatriculation", immatriculation.trim().toLowerCase());
            if (immaCount > 0) {
                System.out.println("test if immaCount > 0");
                if (immaCount > 0) {
                    System.out.println("test if immaCount > 0");
                    Tools.alert(stackPane, anchorPane, "Cet immatriculation existe déjà!");
                }
            } else {
                System.out.println("test if else fin");
                voiture.addVoiture(immatriculation, marque, model, carburant,consomation_moyenne,
                        puissance_fiscale, date_1er_immatru, etat, killometrage, date_dernier_controle_tech,
                        km_dernirer_vidange, km_dernier_courroie);
            }
            if (!(immaCount > 0)) {
                Tools.alert(stackPane, anchorPane, "La voiture a ajouté avec succès!");
                immaVal.setText(null);
                modelVal.setText(null);
                marqueVal.setText(null);
                carbuVal.setText(null);
                etatVal.setValue(null);
                consomation_moyenne_val.setText(null);
                puissance_fiscale_val.setText(null);
                date_1er_immatru_val.setValue(null);
                killometrage_val.setText(null);
                date_dernier_controle_tech_val.setValue(null);
                km_dernirer_vidange_val.setText(null);
                km_dernier_courroie_val.setText(null);
            }
        }

    }

    public void modifier(MouseEvent mouseEvent) {
        con = DataBaseConnection.GetConnection();
        String immatriculation = immaVal.getText();
        String marque = marqueVal.getText();
        String model = modelVal.getText();
        String carburant = carbuVal.getText();
        String etat = etatVal.getSelectionModel().getSelectedItem();
        String consomation_moyenne = consomation_moyenne_val.getText();
        String puissance_fiscale = puissance_fiscale_val.getText();
        String date_1er_immatru = date_1er_immatru_val.getValue() + "";
        String killometrage = killometrage_val.getText();
        String km_dernirer_vidange = km_dernirer_vidange_val.getText();
        String date_dernier_controle_tech = km_dernirer_vidange_val.getText();
        String km_dernier_courroie = km_dernier_courroie_val.getText();
        System.out.println(immatriculation);
        System.out.println(marque);
        System.out.println(model);
        System.out.println(carburant);
        System.out.println(etat);
        System.out.println(consomation_moyenne);
        System.out.println(puissance_fiscale);
        System.out.println(date_1er_immatru);
        System.out.println(killometrage);
        System.out.println(km_dernirer_vidange);
        System.out.println(date_dernier_controle_tech);
        System.out.println(km_dernier_courroie);
        System.out.println(immatriculation.isEmpty() || model.isEmpty() || marque.isEmpty() || carburant.isEmpty() || etat.isEmpty());

        if (immatriculation.isEmpty() || model.isEmpty() || marque.isEmpty() || carburant.isEmpty() || etat.isEmpty()) {
            if (immatriculation.isEmpty()) {
                immaVal.setStyle("-fx-border-color: red;");
                new animatefx.animation.Shake(immaVal).play();
                errorMessage.setVisible(true);
                errorMessage.setText("L'immatriculation est obligatoire!");
                new animatefx.animation.BounceIn(errorMessage).play();
            } else {
                immaVal.setStyle("-fx-border-color: white;");
            }
            if (model.isEmpty()) {
                modelVal.setStyle("-fx-border-color: red;");
                new animatefx.animation.Shake(modelVal).play();
                errorMessage.setVisible(true);
                errorMessage.setText("Le model est obligatoire!");
                new animatefx.animation.BounceIn(errorMessage).play();
            } else {
                modelVal.setStyle("-fx-border-color: white;");
            }
            if (marque.isEmpty()) {
                marqueVal.setStyle("-fx-border-color: red;");
                new animatefx.animation.Shake(marqueVal).play();
                errorMessage.setVisible(true);
                errorMessage.setText("La marque est obligatoire!");
                new animatefx.animation.BounceIn(errorMessage).play();
            } else {
                marqueVal.setStyle("-fx-border-color: white;");
            }

            if (carburant.isEmpty()) {
                carbuVal.setStyle("-fx-border-color: red;");
                new animatefx.animation.Shake(carbuVal).play();
                errorMessage.setVisible(true);
                errorMessage.setText("Le carburant est obligatoire!");
                new animatefx.animation.BounceIn(errorMessage).play();
            }
        } else {
            carbuVal.setStyle("-fx-border-color: white;");

            System.out.println("test else");
            String username = immatriculation + "_" + marque + "_" + new Random(9);
            //           String password = Tools.generatePassword();
            voiture = new Voiture();
            int immaCount = 0;
            System.out.println("test if isedit");
            Tools T = new Tools();
            immaCount = T.testColumnForEdit("voiture", "immatriculation", immatriculation.trim().toLowerCase(), voitureId);
            if (immaCount > 0) {
                System.out.println("test if immaCount > 0");
                if (immaCount > 0) {
                    System.out.println("test if immaCount > 0");
                    Tools.alert(stackPane, anchorPane, "Cet immatriculation existe déjà!");
                }
            } else {
                System.out.println("test if else fin");
                voiture.editVoiture(voitureId, immatriculation, marque, model, carburant, consomation_moyenne,
                        puissance_fiscale, date_1er_immatru, etat, killometrage,
                        date_dernier_controle_tech, km_dernirer_vidange, km_dernier_courroie);
            }
            if (!(immaCount > 0)) {
                Tools.alert(stackPane, anchorPane, "La voiture a été modifier avec succès!");
            }
        }


    }

    public void fillFields(int id, String immatriculation, String marque, String model, String carburant, String consomation_moyenne,
                           String puissance_fiscale, String date_1er_immatru, String etat, String killometrage,
                           String date_dernier_controle_tech, String km_dernirer_vidange, String km_dernier_courroie) {
        voitureId = id;
        immaVal.setText(immatriculation);
        modelVal.setText(model);
        marqueVal.setText(marque);
        carbuVal.setText(carburant);
        etatVal.setValue(etat);
        consomation_moyenne_val.setText(consomation_moyenne);
        puissance_fiscale_val.setText(puissance_fiscale);
        date_1er_immatru_val.setText(date_1er_immatru);
        killometrage_val.setText(killometrage);
        date_dernier_controle_tech_val.setText(date_dernier_controle_tech);
        km_dernirer_vidange_val.setText(km_dernirer_vidange);
        km_dernier_courroie_val.setText(km_dernier_courroie);
    }

    public void setAjouterBtn(boolean visible) {
        this.ajouterBtn.setVisible(visible);
    }

    public void setModifierBtn(boolean visible) {
        this.modifierBtn.setVisible(visible);
    }

    public void pageTitle(String TitleLabel) {
        this.pageTitle.setText(TitleLabel);
    }

    @FXML
    void anuller(MouseEvent event) {
        Stage stage = (Stage) immaVal.getScene().getWindow();
        stage.close();
    }

    public void fillStatus() {
        ObservableList<String> statusList = FXCollections.observableArrayList();
        statusList.clear();
        statusList.add("Bon Etat");
        statusList.add("En pane");
        statusList.add("Moyenne");
        statusList.add("Hors Service");
        etatVal.setItems(null);
        etatVal.setItems(statusList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setVisible(false);
        fillStatus();
    }


}
