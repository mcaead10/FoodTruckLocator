/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Properties {

    private @JsonProperty("Date") String date;
    private @JsonProperty("Heure_debut") String heuredebut;
    private @JsonProperty("Heure_fin") String heurefin;
    private @JsonProperty("Lieu") String lieu;
    private @JsonProperty("Camion") String camion;
    private @JsonProperty("Truckid") String truckid;
    private Date heureDebutDate;
    private Date heureFinDate;
    
    public Properties() {
    }

    public Properties(String lieu, String camion, String truckid, Date heureDebutDate, Date heureFinDate) {
        this.lieu = lieu;
        this.camion = camion;
        this.truckid = truckid;
        this.heureDebutDate = heureDebutDate;
        this.heureFinDate = heureFinDate;
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm z");
        String heureDebut = format.format(heureDebutDate);
       // System.out.println("HEURE_DEBUT =  "+heureDebut);
    }



    public String getDate() {
        return date;
    }

    public String getHeuredebut() {
        return heuredebut;
    }

    public String getHeurefin() {
        return heurefin;
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
