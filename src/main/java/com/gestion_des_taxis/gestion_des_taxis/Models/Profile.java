package com.gestion_des_taxis.gestion_des_taxis.Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Profile {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String Telephone;
    private String email;
    private String Username;
    private String password;
    private String cin;
    private ImageView delete;
    private ImageView icon;

    private String query = null;

    private Connection con = null;

    private PreparedStatement preStatment = null;

    private ResultSet resultSet = null;

    public Profile() {

    }


    public Profile(int id,String nom, String prenom, String adresse, String telephone, String email,
                  String username, String password, ImageView icon) throws FileNotFoundException {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        Telephone = telephone;
        this.email = email;
        Username = username;
        this.password = password;
        this.id = id;
        this.icon = icon;
        FileInputStream input = new FileInputStream("src/main/resources/Images/icons8_Person_32px.png");
        Image image = new Image(input);
        this.icon = new ImageView(image);
        this.icon.setFitWidth(35);
        this.icon.setFitHeight(35);


    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdress() {
        return adresse;
    }

    public void setAdress(String adress) {
        this.adresse = adress;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public ImageView getDelete() {
        return delete;
    }

    public void setDelete(ImageView delete) {
        this.delete = delete;
    }

    public void affichageProfil(String nom, String prenom, String adresse, String telephone, String email,
                          String username, String password,String cin) {
        try {
            con = DataBaseConnection.GetConnection();
            query = "SELECT * FROM profile Where id ="+id;
            preStatment = con.prepareStatement(query);
            preStatment.setString(1, nom);
            preStatment.setString(2, prenom);
            preStatment.setString(3, adresse);
            preStatment.setString(4, telephone);
            preStatment.setString(5, email);
            preStatment.setString(6, username);
            preStatment.setString(7, password);
            preStatment.setString(8, cin);
            preStatment.setString(8, Tools.encryptPassword(password));
            preStatment.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editProfile(int id, String nom, String prenom, String adresse, String telephone, String email,
                            String username, String password,String cin) {
        try {
            con = DataBaseConnection.GetConnection();
            query = "UPDATE `profile` SET `nom`=?,"
                    + "`prenom`=?,`adresse`=?,"
                    + "`telephone`=?,`email`=?,`username`=?,`password`=?,`cin`=?"
                    + "WHERE `Id`='" + id + "'";
            preStatment = con.prepareStatement(query);
            preStatment.setString(1, nom);
            preStatment.setString(2, prenom);
            preStatment.setString(3, adresse);
            preStatment.setString(4, telephone);
            preStatment.setString(5, email);
            preStatment.setString(6, username);
            preStatment.setString(7, password);
            preStatment.setString(8, cin);
            preStatment.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}

