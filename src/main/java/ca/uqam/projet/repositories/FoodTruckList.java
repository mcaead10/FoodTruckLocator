/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.projet.repositories;

import ca.uqam.projet.resources.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.*;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class FoodTruckList {

    private @Autowired
    JdbcTemplate jdbcTemplate;

    private @JsonProperty("features")
    List<FoodTruck> foodTruckList;

    public FoodTruckList() {
        foodTruckList = new ArrayList<>();
    }

    public List<FoodTruck> getFoodTruckList() {
        return foodTruckList;
    }

    public void setFoodTruckList(List<FoodTruck> features) {
        this.foodTruckList = features;
    }

    private static final String INSERT_FOOD_TRUCK
            = "INSERT INTO foodtruck(camion, truckid)"
            + "VALUES (?,?)"
            + "on conflict do nothing";

    private static final String INSERT_POINT_DE_VENTE
            = "INSERT INTO pointdevente(truckid, lieu, longitude, latitude, heure_debut, heure_fin)"
            + "VALUES (?,?,?,?,?,?)"
            + "on conflict do nothing";

    private static final String TIMEZONE = "EDT 2016";

    public void insertAll() {
        Connection conn = connect();
        for (FoodTruck foodTruck : foodTruckList) {
            insertFoodTruck(foodTruck, conn);
            insertPointDeVente(foodTruck, conn);
        }
        diconnect(conn);

    }

    @Override
    public String toString() {
        return "FoodTruckList{" + " foodTruckList=" + foodTruckList + '}';
    }

    private void insertFoodTruck(FoodTruck foodtruck, Connection conn) {

        PreparedStatement ps = null;
        System.out.println("ADD " + foodtruck);
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
