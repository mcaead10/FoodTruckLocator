/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.projet.resources;

public class Properties {

    private String name;
    private String description;
    private String date;
    private String Heure_debut;
    private String Heure_fin;
    private String Lieu;
    private String Camion;
    private String Truckid;

    public Properties() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure_debut() {
        return Heure_debut;
    }

    public void setHeure_debut(String Heure_debut) {
        this.Heure_debut = Heure_debut;
    }

    public String getHeure_fin() {
        return Heure_fin;
    }

    public void setHeure_fin(String Heure_fin) {
        this.Heure_fin = Heure_fin;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public String getCamion() {
        return Camion;
    }

    public void setCamion(String Camion) {
        this.Camion = Camion;
    }

    public String getTruckid() {
        return Truckid;
    }

    public void setTruckid(String Truckid) {
        this.Truckid = Truckid;
    }

    @Override
    public String toString() {
        return "Properties{" + "name=" + name + ", description=" + description + ", date=" + date + ", Heure_debut=" + Heure_debut + ", Heure_fin=" + Heure_fin + ", Lieu=" + Lieu + ", Camion=" + Camion + ", Truckid=" + Truckid + '}';
    }
}
