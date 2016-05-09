package ca.uqam.projet.repositories;

import java.util.*;
import ca.uqam.projet.resources.*;
import org.springframework.stereotype.*;

@Component
public class CitationRepository {

  private static List<Citation> citations = Arrays.asList(
      new Citation(1, "Si l’on sait exactement ce qu’on va faire, à quoi bon le faire ?", "Picasso")
    , new Citation(2, "Le métier, c'est ce qui ne s'apprend pas.", "Picasso")
  );

  public List<Citation> findAll() {
    return citations;
  }

  public Citation findById(int id) {
    return citations.get(id-1);
  }
}
