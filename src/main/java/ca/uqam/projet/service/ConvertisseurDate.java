package ca.uqam.projet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertisseurDate {
    
     private static final SimpleDateFormat formatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm z");
     private static final SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    
    public static Date stringDate(String date) {
        return convertStringDate(date, formatDate);
    }
    
    public static Date stringTimestamp(String date) {
        return convertStringDate(date, formatTimestamp);
    }

    private static Date convertStringDate(String date, SimpleDateFormat format) {
        Date dateDebut = null; 
        try {
            dateDebut = format.parse(date);
        } catch (ParseException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return dateDebut;
    }
    
    public static String dateString(Date date) {
        return formatDate.format(date);
    }
    
    public static String TimestampString(Date date) {
        return formatTimestamp.format(date);
    }

}
