package ca.uqam.projet.repositories;

import ca.uqam.projet.resources.FoodTruck;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BDFoodTruck {

    public BDFoodTruck() {

    }
    private static final String INSERT_FOOD_TRUCK
            = "INSERT INTO foodtruck(camion, truckid)"
            + "VALUES (?,?)"
            + "on conflict do nothing";
    
    private static final String SELECT_DATE
            = "SELECT * FROM foodtruck NATURAL JOIN pointdevente;";

    private static final String INSERT_POINT_DE_VENTE
            = "INSERT INTO pointdevente(truckid, lieu, longitude, latitude, heure_debut, heure_fin)"
            + "VALUES (?,?,?,?,?,?)"
            + "on conflict do nothing";

    private static final String TIMEZONE = "EDT 2016";

    public List<FoodTruck> select() {
        
        List<FoodTruck> list = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = connect();
        try {
            ps = conn.prepareStatement(SELECT_DATE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new FoodTruck(rs.getString("truckid"), 
                                       rs.getString("camion"), 
                                       rs.getString("lieu"),
                                       rs.getInt("longitude"),
                                       rs.getInt("latitude"),
                                       rs.getDate("heure_debut"),
                                       rs.getDate("heure_fin")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
            }
        }
        System.out.println(list);
        return list;
    }

    public void insertAll(FoodTruckList foodTruckList) {
        Connection conn = connect();
        for (FoodTruck foodTruck : foodTruckList.getFoodTruckList()) {
            insertFoodTruck(foodTruck, conn);
            insertPointDeVente(foodTruck, conn);
        }
        diconnect(conn);

    }

    private void insertFoodTruck(FoodTruck foodtruck, Connection conn) {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(INSERT_FOOD_TRUCK);
            ps.setString(1, foodtruck.getProperties().getCamion());
            ps.setString(2, foodtruck.getProperties().getTruckid());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
            }
        }
    }

    private void insertPointDeVente(FoodTruck foodtruck, Connection conn) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm z");
        String date = foodtruck.getProperties().getDate();
        String dateDebutStr = date + " " + foodtruck.getProperties().getHeuredebut() + " " + TIMEZONE;
        String dateFinStr = date + " " + foodtruck.getProperties().getHeurefin() + " " + TIMEZONE;

        PreparedStatement ps = null;
        try {
            Date dateDebut = format.parse(dateDebutStr);
            Date dateFin = format.parse(dateFinStr);
            java.sql.Date sqlDatedebut = new java.sql.Date(dateDebut.getTime());
            java.sql.Date sqlDateFin = new java.sql.Date(dateFin.getTime());

            ps = conn.prepareStatement(INSERT_POINT_DE_VENTE);
            ps.setString(1, foodtruck.getProperties().getTruckid());
            ps.setString(2, foodtruck.getProperties().getLieu());
            ps.setFloat(3, foodtruck.getGeometry().getCoordinates()[0]);
            ps.setFloat(4, foodtruck.getGeometry().getCoordinates()[1]);
            ps.setDate(5, sqlDatedebut);
            ps.setDate(6, sqlDateFin);
            ps.executeUpdate();

        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
            }
        }
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/projet",
                            "postgres", "collin");
            conn.setAutoCommit(true);
            System.out.println("Opened database successfully");
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return conn;
    }

    private void diconnect(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
                System.out.println("Closed database successfully");
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
