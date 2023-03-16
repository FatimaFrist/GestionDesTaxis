package com.gestion_des_taxis.gestion_des_taxis.Controllers;

import com.gestion_des_taxis.gestion_des_taxis.Models.*;
import com.jfoenix.controls.JFXDialog;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXDialog dialog;

    @FXML
    private Label dialogLabel;

    @FXML
    private MFXButton yesButton;

    @FXML
    private MFXButton noButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField nomlVal;

    @FXML
    private TextField prelVal;

    @FXML
    private TextField cneVal;

    @FXML
    private TextField userVal;

    @FXML
    private TextField emailVal;

    @FXML
    private TextField teleVal;

    @FXML
    private TextField AdresseVal;

    @FXML
    private TextField prixTTCVal1111;

    @FXML
    private PasswordField passwordVal;

    @FXML
    private Label pageTitle;

    @FXML
    private PasswordField passwordVal2;

    @FXML
    private Label errorMessage;


    public static int profileId;

    String oldPassword;

    private PreparedStatement preStatment = null;
    private ResultSet resultSet = null;

    String query = null;
    Connection con = null;
    PreparedStatement PreStatment = null;

    Profile profile = null;
    public static ObservableList<Profile> profileList = FXCollections.observableArrayList();


    @FXML
    void cancelProfile(MouseEvent event) {
        //Platform.exit();
    }

    @FXML
    public void fillTable(int id) {
        try {
            con = DataBaseConnection.GetConnection();

            query = "SELECT * FROM Profil WHERE `Id`='" + id + "'";
            preStatment = con.prepareStatement(query);
            preStatment.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void refrechTable() {
        try {
            con = DataBaseConnection.GetConnection();
            query = "SELECT * FROM `admin` WHERE id = " + Admin.corentAdmin;
            PreStatment = con.prepareStatement(query);
            resultSet = PreStatment.executeQuery();
            resultSet.next();
            cneVal.setText(resultSet.getString(2));
            nomlVal.setText(resultSet.getString(3));
            prelVal.setText(resultSet.getString(4));
            AdresseVal.setText(resultSet.getString(5));
            teleVal.setText(resultSet.getString(6));
            emailVal.setText(resultSet.getString(7));
            userVal.setText(resultSet.getString(8));
            oldPassword = resultSet.getString(9);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void modifier(MouseEvent event) {
        try {
            con = DataBaseConnection.GetConnection();
            String cne = cneVal.getText();
            String nom = nomlVal.getText();
            String prenom = prelVal.getText();
            String adresse = AdresseVal.getText();
            String telephone = teleVal.getText();
            String email = emailVal.getText();
            String username = userVal.getText();
            String password = passwordVal.getText();
            String password2 = passwordVal2.getText();

            int telephoneCount = 0;
            int emailCount = 0;
            if (cne.isEmpty() || nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || telephone.isEmpty() || email.isEmpty() || (!(email.matches("[a-zA-Z0-9_@.]{10,}")))
                    || (!(telephone.matches("[0-9+]{9,}"))) || username.isEmpty()) {
                if (cne.isEmpty()) {
                    cneVal.setStyle("-fx-border-color: red;");
                    new animatefx.animation.Shake(cneVal).play();
                    errorMessage.setVisible(true);
                    errorMessage.setText("Le cne est obligatoire!");
                    new animatefx.animation.BounceIn(errorMessage).play();
                } else {
                    cneVal.setStyle("-fx-border-color: white;");
                }
                if (nom.isEmpty()) {
                    nomlVal.setStyle("-fx-border-color: red;");
                    new animatefx.animation.Shake(nomlVal).play();
                    errorMessage.setVisible(true);
                    errorMessage.setText("Le nom est obligatoire!");
                    new animatefx.animation.BounceIn(errorMessage).play();
                } else {
                    nomlVal.setStyle("-fx-border-color: white;");
                }
                if (prenom.isEmpty()) {
                    prelVal.setStyle("-fx-border-color: red;");
                    new animatefx.animation.Shake(prelVal).play();
                    errorMessage.setVisible(true);
                    errorMessage.setText("Le prenom est obligatoire!");
                    new animatefx.animation.BounceIn(errorMessage).play();
                } else {
                    prelVal.setStyle("-fx-border-color: white;");
                }
                if (telephone.isEmpty()) {
                    teleVal.setStyle("-fx-border-color: red;");
                    new animatefx.animation.Shake(teleVal).play();
                    errorMessage.setVisible(true);
                    errorMessage.setText("Le numero de téléphone est obligatoire!");
                    new animatefx.animation.BounceIn(errorMessage).play();
                } else {
                    teleVal.setStyle("-fx-border-color: white;");
                }

                if (adresse.isEmpty()) {
                    AdresseVal.setStyle("-fx-border-color: red;");
                    new animatefx.animation.Shake(AdresseVal).play();
                    errorMessage.setVisible(true);
                    errorMessage.setText("L'adresse est obligatoire!");
                    new animatefx.animation.BounceIn(errorMessage).play();
                } else {
                    AdresseVal.setStyle("-fx-border-color: white;");
                }
                if (username.isEmpty()) {
                    userVal.setStyle("-fx-border-color: red;");
                    new animatefx.animation.Shake(userVal).play();
                    errorMessage.setVisible(true);
                    errorMessage.setText("Le username est obligatoire!");
                    new animatefx.animation.BounceIn(errorMessage).play();
                } else {
                    userVal.setStyle("-fx-border-color: white;");
                }

                if (email.isEmpty()) {
                    emailVal.setStyle("-fx-border-color: red;");
                    new animatefx.animation.Shake(emailVal).play();
                    errorMessage.setVisible(true);
                    errorMessage.setText("L'E-mail est obligatoire!");
                    new animatefx.animation.BounceIn(errorMessage).play();
                } else {
                    emailVal.setStyle("-fx-border-color: white;");
                }
                if (!(email.matches("[a-zA-Z0-9_@.]{10,}")) && (!(email.isEmpty()))) {
                    emailVal.setStyle("-fx-border-color: red;");
                    new animatefx.animation.Shake(emailVal).play();
                    errorMessage.setVisible(true);
                    errorMessage.setText("E-mail est incorrect!");
                    new animatefx.animation.BounceIn(errorMessage).play();
                }
                if (!(telephone.matches("[0-9+]{9,}")) && (!(telephone.isEmpty()))) {
                    teleVal.setStyle("-fx-border-color: red;");
                    new animatefx.animation.Shake(teleVal).play();
                    errorMessage.setVisible(true);
                    errorMessage.setText("Le numero de téléphone est incorrect!");
                    new animatefx.animation.BounceIn(errorMessage).play();
                }
                System.out.println("test if");

            } else {
                if (!password.isEmpty() || !password2.isEmpty() || (!password.equals(oldPassword) && !password.isEmpty())) {
                    if (password.isEmpty()) {
                        passwordVal.setStyle("-fx-border-color: red;");
                        new animatefx.animation.Shake(passwordVal).play();
                        errorMessage.setVisible(true);
                        errorMessage.setText("Ancien Mot de passe est obligatoire!");
                        new animatefx.animation.BounceIn(errorMessage).play();
                    } else {
                        passwordVal.setStyle("-fx-border-color: white;");
                    }

                    if (password2.isEmpty()) {
                        passwordVal2.setStyle("-fx-border-color: red;");
                        new animatefx.animation.Shake(passwordVal2).play();
                        errorMessage.setVisible(true);
                        errorMessage.setText("Nouveau Mot de passe est obligatoire!");
                        new animatefx.animation.BounceIn(errorMessage).play();
                    } else {
                        passwordVal2.setStyle("-fx-border-color: white;");
                    }

                    if (!password.equals(oldPassword)) {
                        passwordVal.setStyle("-fx-border-color: red;");
                        new animatefx.animation.Shake(passwordVal).play();
                        errorMessage.setVisible(true);
                        errorMessage.setText("Ancien Mot de passe est incorrect!");
                        new animatefx.animation.BounceIn(errorMessage).play();
                    } else {
                        passwordVal.setStyle("-fx-border-color: white;");
                        query = "UPDATE `admin` SET `cne`=?,`nom`=?,"
                                + "`prenom`=?,`adresse`=?,"
                                + "`telephone`=?,`email`=?,`username`=?,`password`=?"
                                + "WHERE `Id`='" + +Admin.corentAdmin + "'";
                        preStatment = con.prepareStatement(query);
                        preStatment.setString(1, cneVal.getText());
                        preStatment.setString(2, nomlVal.getText());
                        preStatment.setString(3, prelVal.getText());
                        preStatment.setString(4, AdresseVal.getText());
                        preStatment.setString(5, teleVal.getText());
                        preStatment.setString(6, emailVal.getText());
                        preStatment.setString(7, userVal.getText());
                        preStatment.setString(8, passwordVal2.getText());
                        preStatment.execute();
                        Tools.alert(stackPane, anchorPane, "Le profile a été mettre a jour avec succès!");
                    }
                }
                System.out.println("test if else fin");

//                cneVal.setStyle("-fx-border-color: white;");
//                nomlVal.setStyle("-fx-border-color: white;");
//                prelVal.setStyle("-fx-border-color: white;");
//                teleVal.setStyle("-fx-border-color: white;");
//                AdresseVal.setStyle("-fx-border-color: white;");
//                emailVal.setStyle("-fx-border-color: white;");
//                userVal.setStyle("-fx-border-color: white;");
//                passwordVal.setStyle("-fx-border-color: white;");
                if(password2.isEmpty() && password.isEmpty()) {
                    query = "UPDATE `admin` SET `cne`=?,`nom`=?,"
                            + "`prenom`=?,`adresse`=?,"
                            + "`telephone`=?,`email`=?,`username`=?"
                            + "WHERE `Id`='" + +Admin.corentAdmin + "'";
                    preStatment = con.prepareStatement(query);
                    preStatment.setString(1, cneVal.getText());
                    preStatment.setString(2, nomlVal.getText());
                    preStatment.setString(3, prelVal.getText());
                    preStatment.setString(4, AdresseVal.getText());
                    preStatment.setString(5, teleVal.getText());
                    preStatment.setString(6, emailVal.getText());
                    preStatment.setString(7, userVal.getText());
                    preStatment.execute();
                    Tools.alert(stackPane, anchorPane, "Le profile a été mettre a jour avec succès!");
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setVisible(false);
        refrechTable();
    }
}
