package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)

public class FoodTruck {
  private int id;
  private String contenu;
  private String auteur;

  public FoodTruck(int id, String contenu, String auteur) {
    this.id = id;
    this.contenu = contenu;
    this.auteur = auteur;
  }
@JsonProperty("Heure_debut") String heureDebut;

  @JsonProperty public int getId() { return id; }
  @JsonProperty public String getContenu() { return contenu; }
  @JsonProperty public String getAuteur() { return auteur; }

}
