package ca.uqam.projet.resources;

import com.fasterxml.jackson.annotation.*;

public class Citation {
  private int id;
  private String contenu;
  private String auteur;

  public Citation(int id, String contenu, String auteur) {
    this.id = id;
    this.contenu = contenu;
    this.auteur = auteur;
  }

  @JsonProperty public int getId() { return id; }
  @JsonProperty public String getContenu() { return contenu; }
  @JsonProperty public String getAuteur() { return auteur; }

}
