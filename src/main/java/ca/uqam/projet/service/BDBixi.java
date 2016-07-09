package ca.uqam.projet.service;

import ca.uqam.projet.repositories.BixiList;
import ca.uqam.projet.resources.Bixi;
import static ca.uqam.projet.service.BD.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BDBixi extends BD {

    private static final String INSERT_BIXI
            = "INSERT INTO bixi(id, name, geog, ouvert, veloDisponible, emplacementDisponible)"
            + "VALUES (?,?,ST_GeographyFromText('POINT(' || ? || ' ' || ? || ')'),?,?,?)"
            + "ON CONFLICT(id)"
            + "DO UPDATE SET velodisponible = EXCLUDED.velodisponible, "
            + "emplacementdisponible = EXCLUDED.emplacementdisponible";

    private static final String SELECT_PROCHE
            = "SELECT *,ST_X(geog::geometry) AS longitude, ST_Y(geog::geometry) AS latitude "
            + "FROM  bixi where  ST_Distance( geog, ST_GeographyFromText('POINT(' || ? || ' ' || ? || ')')) < 200 ;";

    public static void insertAll(BixiList bixiList) {
        Connection conn = connect();
        for (Bixi bixi : bixiList.getBixiList()) {
            insertBixi(bixi, conn);
        }
        diconnect(conn);
    }

    public static List<Bixi> Select(float longiture, float latitude) {
        List<Bixi> list = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = connect();
        try {
            ps = conn.prepareStatement(SELECT_PROCHE);
            ps.setFloat(1, longiture);
            ps.setFloat(2, latitude);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                list.add(new Bixi(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("longitude"),
                        rs.getFloat("latitude"),
                        rs.getBoolean("ouvert"),
                        rs.getInt("velodisponible"),
                        rs.getInt("emplacementdisponible")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            CloseConnection(ps);
        }
        diconnect(conn);
        return list;
    }

    private static void insertBixi(Bixi bixi, Connection conn) {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(INSERT_BIXI);
            ps.setInt(1, bixi.getId());
            ps.setString(2, bixi.getName());
            ps.setFloat(3, bixi.getLongitude());
            ps.setFloat(4, bixi.getLatitude());
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
