package com.gestion_des_taxis.gestion_des_taxis.Models;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Voiture {
    private int id;
    private String immatriculation;
    private String marque;
    private String model;
    private String carburant;
    private String consomation_moyenne;
    private String puissance_fiscale;
    private String date_1er_immatru;
    private String etat;
    private String killometrage;
    private String date_dernier_controle_tech;

    private String km_dernirer_vidange;

    private String km_dernier_vidange;
    private String km_dernier_courroie;
    private String img_path;

    private String query = null;
    private Connection con = null;
    private PreparedStatement preStatment = null;
    private ResultSet resultSet = null;


    public Voiture() {
    }

    public Voiture(int id, String immatriculation, String marque, String model, String carburant, String consomation_moyenne,
                   String puissance_fiscale, String date_1er_immatru, String etat, String killometrage, String date_dernier_controle_tech,
                   String km_dernier_vidange, String km_dernier_courroie, String img_path){
        this.id = id;
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.model = model;
        this.carburant = carburant;
        this.consomation_moyenne = consomation_moyenne;
        this.puissance_fiscale = puissance_fiscale;
        this.date_1er_immatru = date_1er_immatru;
        this.etat = etat;
        this.killometrage = killometrage;
        this.date_dernier_controle_tech = date_dernier_controle_tech;
        this.km_dernier_vidange = km_dernier_vidange;
        this.km_dernier_courroie = km_dernier_courroie;
        this.img_path = img_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public String getConsomation_moyenne() {
        return consomation_moyenne;
    }

    public void setConsomation_moyenne(String consomation_moyenne) {
        this.consomation_moyenne = consomation_moyenne;
    }

    public String getPuissance_fiscale() {
        return puissance_fiscale;
    }

    public void setPuissance_fiscale(String puissance_fiscale) {
        this.puissance_fiscale = puissance_fiscale;
    }

    public String getDate_1er_immatru() {
        return date_1er_immatru;
    }

    public void setDate_1er_immatru(String date_1er_immatru) {
        this.date_1er_immatru = date_1er_immatru;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getKillometrage() {
        return killometrage;
    }

    public void setKillometrage(String killometrage) {
        this.killometrage = killometrage;
    }

    public String getDate_dernier_controle_tech() {
        return date_dernier_controle_tech;
    }

    public void setDate_dernier_controle_tech(String date_dernier_controle_tech) {
        this.date_dernier_controle_tech = date_dernier_controle_tech;
    }

    public String getKm_dernier_vidange() {
        return km_dernier_vidange;
    }

    public void setKm_dernier_vidange(String km_dernier_vidange) {
        this.km_dernier_vidange = km_dernier_vidange;
    }

    public String getKm_dernier_courroie() {
        return km_dernier_courroie;
    }

    public void setKm_dernier_courroie(String km_dernier_courroie) {
        this.km_dernier_courroie = km_dernier_courroie;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public void addVoiture(String immatriculation, String marque, String model, String carburant, String consomation_moyenne,
                           String puissance_fiscale,String date_1er_immatru,String etat,String killometrage,
                           String date_dernier_controle_tech,String km_dernirer_vidange,String km_dernier_courroie){
        try {
            con = DataBaseConnection.GetConnection();
            query = "INSERT INTO `voiture` (`immatriculation`, `marque`, `model`, `carburant`, `consomation_moyenne`, `puissance_fiscale`," +
                    "`date_1er_immatru`, `etat`, `killometrage`, `date_dernier_controle_tech`, `km_dernirer_vidange`, " +
                    "`km_dernier_courroie`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            preStatment = con.prepareStatement(query);
            preStatment.setString(1, immatriculation);
            preStatment.setString(2, marque);
            preStatment.setString(3, model);
            preStatment.setString(4, carburant);
            preStatment.setString(5, consomation_moyenne);
            preStatment.setString(6, puissance_fiscale);
            preStatment.setString(7, date_1er_immatru);
            preStatment.setString(8, etat);
            preStatment.setString(9, killometrage);
            preStatment.setString(10, date_dernier_controle_tech);
            preStatment.setString(11, km_dernirer_vidange);
            preStatment.setString(12, km_dernier_courroie);
            preStatment.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public void editVoiture(int id, String immatriculation, String marque, String model, String carburant, String consomation_moyenne,
                            String puissance_fiscale,String date_1er_immatru,String etat,String killometrage,
                            String date_dernier_controle_tech,String km_dernirer_vidange,String km_dernier_courroie) {
        try {
            con = DataBaseConnection.GetConnection();
            query = "UPDATE `voiture` SET `immatriculation`=?,"
                    + "`marque`=?,`model`=?,"
                    +"`etat`=?,`killometrage`=?,"
                    +"`puissance_fiscale`=?,`date_1er_immatru`=?,"
                    +"`date_dernier_controle_tech`=?,`km_dernirer_vidange`=?,"
                    +"`carburant`=?,`consomation_moyenne`=?,"
                    + "`km_dernier_courroie`=? WHERE `Id`='" + id + "'";
            preStatment = con.prepareStatement(query);
            preStatment.setString(1, immatriculation);
            preStatment.setString(2, marque);
            preStatment.setString(3, model);
            preStatment.setString(4, carburant);
            preStatment.setString(5, consomation_moyenne);
            preStatment.setString(6, puissance_fiscale);
            preStatment.setString(7, date_1er_immatru);
            preStatment.setString(8, etat);
            preStatment.setString(9, killometrage);
            preStatment.setString(10, date_dernier_controle_tech);
            preStatment.setString(11, km_dernirer_vidange);
            preStatment.setString(12, km_dernier_courroie);
            preStatment.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void deleteVoiture(){}
    public void asearchVoiture(){}
    public void generateFichTech(){}
    public void exportVoitures(){
        try {
            query = "SELECT * FROM voiture";
            con = DataBaseConnection.GetConnection();
            preStatment = con.prepareStatement(query);
            resultSet = preStatment.executeQuery();

            XSSFWorkbook XFWB = new XSSFWorkbook();
            XSSFSheet XFSheet = XFWB.createSheet("Voitures List");
            XSSFRow HeaderRow = XFSheet.createRow(0);
            HeaderRow.createCell(0).setCellValue("Id");
            HeaderRow.createCell(1).setCellValue("Matricule");
            HeaderRow.createCell(2).setCellValue("Marque");
            HeaderRow.createCell(3).setCellValue("Model");
            HeaderRow.createCell(4).setCellValue("Carburant");
            HeaderRow.createCell(5).setCellValue("Consomation Moyenne");
            HeaderRow.createCell(6).setCellValue("Puissance Fiscale");
            HeaderRow.createCell(7).setCellValue("Date 1er Immatrucule");
            HeaderRow.createCell(8).setCellValue("Etat");
            HeaderRow.createCell(9).setCellValue("Killometrage");
            HeaderRow.createCell(10).setCellValue("Date Dernier Controle Tech");
            HeaderRow.createCell(11).setCellValue("Km Dernirer Vidange");
            HeaderRow.createCell(12).setCellValue("Km Dernier Courroie");
            HeaderRow.createCell(13).setCellValue("Deleted At");


            int RowNum = 1;
            while (resultSet.next()) {
                XSSFRow Row = XFSheet.createRow(RowNum);
                Row.createCell(0).setCellValue(resultSet.getInt(1));
                Row.createCell(1).setCellValue(resultSet.getString(2));
                Row.createCell(2).setCellValue(resultSet.getString(3));
                Row.createCell(3).setCellValue(resultSet.getString(4));
                Row.createCell(4).setCellValue(resultSet.getString(5));
                Row.createCell(5).setCellValue(resultSet.getString(6));
                Row.createCell(6).setCellValue(resultSet.getString(7));
                Row.createCell(7).setCellValue(resultSet.getString(8));
                Row.createCell(8).setCellValue(resultSet.getString(9));
                Row.createCell(9).setCellValue(resultSet.getString(10));
                Row.createCell(10).setCellValue(resultSet.getString(11));
                Row.createCell(11).setCellValue(resultSet.getString(12));
                Row.createCell(12).setCellValue(resultSet.getString(13));
                Row.createCell(13).setCellValue(resultSet.getString(14));
                RowNum++;
            }
            try (FileOutputStream FileOutStr = new FileOutputStream("Exported/Voitures.xlsx")) {
                XFWB.write(FileOutStr);
                System.out.println("Okay");
            }
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void softDelete(int id) {

        try {
            con = DataBaseConnection.GetConnection();
            query = "UPDATE `voiture` SET `soft_delete`= CURRENT_TIMESTAMP "
                    + "WHERE `Id`='" + id + "'";
            preStatment = con.prepareStatement(query);
            preStatment.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void hardDeletionVoiture(int id)  {
        try {
            query = "DELETE FROM `voiture`  where id = "+ id;
            con = DataBaseConnection.GetConnection();
            preStatment = con.prepareStatement(query);
            preStatment.execute();
        }catch (SQLException ex) {
        }


    }

    public void restorVoiture(int id)
    {
        try {
            con = DataBaseConnection.GetConnection();
            query = "UPDATE `voiture` SET `soft_delete`= null "
                    + "WHERE `Id`='" + id + "'";
            preStatment = con.prepareStatement(query);
            preStatment.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
