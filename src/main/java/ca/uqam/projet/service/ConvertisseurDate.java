package ca.uqam.projet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertisseurDate {
    
     private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm z");
    
    public static Date stringDate(String date) {
        ////////est ce qu'il faut garder l'historique ou on peut delete la table a chaque ajout dans la bd?
        Date dateDebut = null; 
        try {
            dateDebut = format.parse(date);
        } catch (ParseException e) {
           System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return dateDebut;
    }
    
    public static String dateString(Date date) {
        return format.format(date);
    }

}
