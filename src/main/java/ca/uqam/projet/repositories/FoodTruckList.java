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
import java.util.*;
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
            = "INSERT INTO pointdevente(truckid, lieu, longiture, latitude, jour, heure_debut, heure fin)"
            + "VALUES (?,?,?,?,?,?,?)"
            + "on conflict do nothing";

    public void insertAll() {
        Connection conn = connect();
        for (FoodTruck foodTruck : foodTruckList) {
            insertFoodTruck(foodTruck, conn);
            //   insertPointDeVente(foodTruck);
        }
        System.out.println("done");
        return;
    }

    @Override
    public String toString() {
        return "FoodTruckList{" + " foodTruckList=" + foodTruckList + '}';
    }

    private void insertFoodTruck(FoodTruck foodtruck, Connection conn) {

        PreparedStatement ps = null;
        System.out.println("ADD "+ foodtruck);
        try {
            ps = conn.prepareStatement(INSERT_FOOD_TRUCK);
            ps.setString(1, foodtruck.getProperties().getCamion());
            ps.setString(2, foodtruck.getProperties().getTruckid());
//            int line = ps.executeUpdate();
          ps.execute();
//            System.out.println("LINE : "+ line);

        } catch (SQLException e) {
            System.out.println("ERREURR");
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                try {
                        ps.close();
                } catch (SQLException e) {
                    System.out.println("ERREURR");
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
            }
        }
    }

    private void insertPointDeVente(FoodTruck foodtruck) {
//        return jdbcTemplate.update(conn -> {
//            PreparedStatement ps = conn.PreparedStatement(INSERT_POINT_DE_VENTE);
//            ps.setInt(1, x);
//
//        }
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
            if (conn!= null && !conn.isClosed()){
                conn.close();
                conn= null;
                System.out.println("Closed database successfully");
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
