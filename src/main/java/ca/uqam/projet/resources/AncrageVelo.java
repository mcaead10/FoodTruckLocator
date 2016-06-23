
package ca.uqam.projet.resources;
import com.opencsv.bean.*;

public class AncrageVelo {
    @CsvBind
    private String LONG;
    @CsvBind
    private String LAT;


    @Override
    public String toString() {
        return "AncrageVelo{" + "x=" + LONG + ", y=" + LAT + '}';
    }
    
}
