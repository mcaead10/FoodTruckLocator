package ca.uqam.projet.service;

import ca.uqam.projet.repositories.FoodTruckList;
import ca.uqam.projet.resources.FoodTruck;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BDFoodTruck extends BD {

    private static final String INSERT_FOOD_TRUCK
            = "INSERT INTO foodtruck(camion, truckid)"
            + "VALUES (?,?)"
            + "ON CONFLICT(truckid)"
            + "DO UPDATE SET camion = EXCLUDED.camion";

    private static final String SELECT_DATE
            = "SELECT * FROM foodtruck NATURAL JOIN pointdevente WHERE heure_debut >= ? AND heure_fin <= ? ;";

    private static final String INSERT_POINT_DE_VENTE
            = "INSERT INTO pointdevente(truckid, lieu, longitude, latitude, heure_debut, heure_fin)"
            + "VALUES (?,?,?,?,?,?)"
            + "on conflict do nothing";
    
    private static final String DELETE_POINT_DE_VENTE = "delete from pointdevente ;";
    
    private static final String DELETE_FOOD_TRUCK = "delete from foodtruck ;";

    private static final String TIMEZONE = "EDT 2016";
    private static final String ENDTIME = " 23:59 ";

    public static List<FoodTruck> select(String dateDebut, String dateFin) {

        List<FoodTruck> list = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = connect();
        dateFin += ENDTIME + TIMEZONE;
        try {
            ps = conn.prepareStatement(SELECT_DATE);
            ps.setTimestamp(1, new java.sql.Timestamp(ConvertisseurDate.stringDate(dateDebut).getTime()));
            ps.setTimestamp(2, new java.sql.Timestamp(ConvertisseurDate.stringTimestamp(dateFin).getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new FoodTruck(rs.getString("truckid"),
                        rs.getString("camion"),
                        rs.getString("lieu"),
                        rs.getFloat("longitude"),
                        rs.getFloat("latitude"),
                        rs.getTimestamp("heure_debut"),
                        rs.getTimestamp("heure_fin")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            CloseConnection(ps);
        }
        diconnect(conn);
        return list;
    }

    public static void insertAll(FoodTruckList foodTruckList) {
        Connection conn = connect();
        deleteFoodTruck(conn);
        for (FoodTruck foodTruck : foodTruckList.getFoodTruckList()) {
            insertFoodTruck(foodTruck, conn);
            insertPointDeVente(foodTruck, conn);
        }
        diconnect(conn);
    }

    private static void insertFoodTruck(FoodTruck foodtruck, Connection conn) {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(INSERT_FOOD_TRUCK);
            ps.setString(1, foodtruck.getProperties().getCamion());
            ps.setString(2, foodtruck.getProperties().getTruckid());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            CloseConnection(ps);
        }
    }

    private static void insertPointDeVente(FoodTruck foodtruck, Connection conn) {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(INSERT_POINT_DE_VENTE);
            ps.setString(1, foodtruck.getProperties().getTruckid());
            ps.setString(2, foodtruck.getProperties().getLieu());
            ps.setFloat(3, foodtruck.getGeometry().getCoordinates()[0]);
            ps.setFloat(4, foodtruck.getGeometry().getCoordinates()[1]);
            ps.setTimestamp(5, new java.sql.Timestamp(foodtruck.getProperties().getHeureDebutDate().getTime()));
            ps.setTimestamp(6, new java.sql.Timestamp(foodtruck.getProperties().getHeureFinDate().getTime()));
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            CloseConnection(ps);
        }
    }
    
    private static void deleteFoodTruck(Connection conn) {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(DELETE_POINT_DE_VENTE);
            ps.executeUpdate();
            ps = conn.prepareStatement(DELETE_FOOD_TRUCK);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            CloseConnection(ps);
        }
    }
}
