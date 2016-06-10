/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.projet.resources;

import ca.uqam.projet.service.ConvertisseurDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Properties {

    private @JsonProperty("Date")
    String date;
    private @JsonProperty("Heure_debut")
    String heuredebut;
    private @JsonProperty("Heure_fin")
    String heurefin;
    private @JsonProperty("Lieu")
    String lieu;
    private @JsonProperty("Camion")
    String camion;
    private @JsonProperty("Truckid")
    String truckid;
    private Date heureDebutDate;
    private Date heureFinDate;

    private static final String TIMEZONE = "EDT 2016";

    public Properties() {
    }

    public Properties(String lieu, String camion, String truckid, Date heureDebutDate, Date heureFinDate) {
        this.lieu = lieu;
        this.camion = camion;
        this.truckid = truckid;
        this.heureDebutDate = heureDebutDate;
        this.heureFinDate = heureFinDate;
        this.heuredebut = troncHeure(ConvertisseurDate.dateString(heureDebutDate));
        this.heurefin = troncHeure(ConvertisseurDate.dateString(heureFinDate));
        this.date = troncDate(ConvertisseurDate.dateString(heureFinDate));
    }

    private String dateHeure(String heure) {
        return date + " " + heure + " " + TIMEZONE;
    }

    private String troncHeure(String date) {
        return date;
    }
    private String troncDate(String date) {
        return date;
    }

    public Date getHeureDebutDate() {
        if (heureDebutDate != null) {
            return heureDebutDate;
        }
        return ConvertisseurDate.stringDate(dateHeure(heuredebut));
    }

    public Date getHeureFinDate() {
        if (heureFinDate != null) {
            return heureFinDate;
        }
        return ConvertisseurDate.stringDate(dateHeure(heurefin));
    }

    public String getLieu() {
        return lieu;
    }

    public String getCamion() {
        return camion;
    }

    public String getTruckid() {
        return truckid;
    }

    @Override
    public String toString() {
        return "Properties{" + ", camion=" + camion + ", truckid=" + truckid + "lieu=" + lieu + ", heureDebutDate=" + heureDebutDate + ", heureFinDate=" + heureFinDate + '}';
    }

}
