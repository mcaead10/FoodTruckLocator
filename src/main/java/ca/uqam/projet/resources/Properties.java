package ca.uqam.projet.resources;

import ca.uqam.projet.service.ConvertisseurDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

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
        this.heuredebut = troncHeure(ConvertisseurDate.TimestampString(heureDebutDate));
        this.heurefin = troncHeure(ConvertisseurDate.TimestampString(heureFinDate));
        this.date = troncDate(ConvertisseurDate.TimestampString(heureFinDate));
    }

    private String dateHeure(String heure) {
        return date + " " + heure + " " + TIMEZONE;
    }

    private String troncHeure(String date) {
        String[] parts = date.split(" ");
        return parts[1];
    }

    private String troncDate(String date) {
        String[] parts = date.split(" ");
        return parts[0];
    }

    public Date getHeureDebutDate() {
        if (heureDebutDate != null) {
            return heureDebutDate;
        }
        return ConvertisseurDate.stringTimestamp(dateHeure(heuredebut));
    }

    public Date getHeureFinDate() {
        if (heureFinDate != null) {
            return heureFinDate;
        }
        return ConvertisseurDate.stringTimestamp(dateHeure(heurefin));
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
        return "Properties{" + "lieu=" + lieu + ", camion=" + camion + ", truckid=" + truckid + ", heureDebutDate=" + heureDebutDate + ", heureFinDate=" + heureFinDate + '}';
    }

}
