package ca.uqam.projet.service;

import ca.uqam.projet.repositories.BixiList;
import ca.uqam.projet.resources.Bixi;
import static ca.uqam.projet.service.BD.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BDBixi extends BD {
    
        private static final String INSERT_BIXI
            = "INSERT INTO bixi(id, name, longitude, latitude, ouvert, veloDisponible, emplacementDisponible)"
            + "VALUES (?,?,?,?,?,?,?)"
            + "on conflict do nothing";

    public static void insertAll(BixiList bixiList) {
        Connection conn = connect();
        for (Bixi bixi : bixiList.getBixiList()) {
            insertBixi(bixi, conn);
        }
        diconnect(conn);
    }

    private static void insertBixi(Bixi bixi, Connection conn) {
       
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(INSERT_BIXI);
            ps.setInt(1, bixi.getId());
            ps.setString(2, bixi.getName());
            ps.setFloat(3, bixi.getX());
            ps.setFloat(4, bixi.getY());
            ps.setBoolean(5, bixi.isOuvert());
            ps.setInt(6, bixi.getVeloDisponible());
            ps.setInt(7, bixi.getEmplacementDisponible());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            CloseConnection(ps);
        }
    }

}
