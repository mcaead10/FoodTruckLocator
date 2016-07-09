package ca.uqam.projet.service;

import ca.uqam.projet.repositories.AncrageVeloList;
import ca.uqam.projet.resources.AncrageVelo;
import static ca.uqam.projet.service.BD.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BDAncrageVelo extends BD {

    private static final String INSERT_ANCRAGE_VELO
            = "INSERT INTO ancragevelo(geog)"
            + "VALUES (ST_GeographyFromText('POINT(' || ? || ' ' || ? || ')'))"
            + "on conflict do nothing";
    
        private static final String SELECT_PROCHE
            = "SELECT *,ST_X(geog::geometry) AS longitude, ST_Y(geog::geometry) AS latitude "
            + "FROM  ancragevelo where  ST_Distance( geog, ST_GeographyFromText('POINT(' || ? || ' ' || ? || ')')) <= 200 ;";

    public static void insertAll(AncrageVeloList ancrageVeloList) {
        Connection conn = connect();
        for (AncrageVelo ancrageVelo : ancrageVeloList.getAncrageVelosList()) {
            insertAncrageVelo(ancrageVelo, conn);
        }
        diconnect(conn);
    }

    private static void insertAncrageVelo(AncrageVelo ancrageVelo, Connection conn) {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(INSERT_ANCRAGE_VELO);
            ps.setFloat(1, ancrageVelo.getLongitude());
            ps.setFloat(2, ancrageVelo.getLatitude());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            CloseConnection(ps);
        }
    }

    public static List<AncrageVelo> Select(float longitude, float latitude) {
        {
        List<AncrageVelo> list = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = connect();
        try {
            ps = conn.prepareStatement(SELECT_PROCHE);
            ps.setFloat(1, longitude);
            ps.setFloat(2, latitude);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new AncrageVelo(
                        rs.getFloat("longitude"),
                        rs.getFloat("latitude")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            CloseConnection(ps);
        }
        diconnect(conn);
        return list;
    }

    }
}
