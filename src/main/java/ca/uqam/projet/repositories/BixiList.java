
package ca.uqam.projet.repositories;

import ca.uqam.projet.resources.Bixi;
import java.util.ArrayList;
import java.util.List;

public class BixiList {
        private final List<Bixi> bixiList;

    public BixiList() {
        bixiList = new ArrayList<>();
    }

    public List<Bixi> getBixiList() {
        return bixiList;
    }

    public void addBixi(Bixi bixi) {
        this.bixiList.add(bixi);
    }
}
