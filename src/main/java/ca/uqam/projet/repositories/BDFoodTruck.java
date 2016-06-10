package ca.uqam.projet.repositories;

import ca.uqam.projet.resources.FoodTruck;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BDFoodTruck {

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

    public static List<FoodTruck> select() {

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
                        rs.getTimestamp("heure_debut"),
                        rs.getTimestamp("heure_fin")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            CloseConnection(ps);
        }
        System.out.println(list);
        return list;
    }

    public static void insertAll(FoodTruckList foodTruckList) {
        Connection conn = connect();
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
        ////////est ce qu'il faut garder l'historique ou on peut delete la table a chaque ajout dans la bd?

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

    private static void CloseConnection(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    }

    private static Connection connect() {
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

    private static void diconnect(Connection conn) {
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