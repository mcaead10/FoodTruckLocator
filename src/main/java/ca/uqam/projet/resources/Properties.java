/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties {

    private @JsonProperty("name") String name;
    private @JsonProperty("description") String description;
    private @JsonProperty("Date") String date;
    private @JsonProperty("Heure_debut") String heuredebut;
    private @JsonProperty("Heure_fin") String heurefin;
    private @JsonProperty("Lieu") String lieu;
    private @JsonProperty("Camion") String camion;
    private @JsonProperty("Truckid") String truckid;

    public Properties() {
    }

    Properties(String truckid, String camion) {
        name = "";
        description = "";
        date = "";
        heuredebut = "";
        heurefin = "";
        lieu = "";
        this.camion = camion;
        this.truckid = truckid;
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
        return "Properties{" + "name=" + name + ", description=" + description + ", date=" + date + ", heuredebut=" + heuredebut + ", heurefin=" + heurefin + ", lieu=" + lieu + ", camion=" + camion + ", truckid=" + truckid + '}';
    }

}
