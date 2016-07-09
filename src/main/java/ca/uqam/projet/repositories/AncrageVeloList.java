package ca.uqam.projet.repositories;

import ca.uqam.projet.resources.AncrageVelo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AncrageVeloList {
        private final List<AncrageVelo> ancrageVeloList;

    public AncrageVeloList() {
        ancrageVeloList = new ArrayList<>();
    }

    public List<AncrageVelo> getAncrageVelosList() {
        return ancrageVeloList;
    }

    public void addAncrageVelo(AncrageVelo ancrageVelo) {
        this.ancrageVeloList.add(ancrageVelo);
    }
}
