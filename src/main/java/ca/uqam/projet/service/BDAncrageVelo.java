package ca.uqam.projet.service;

import ca.uqam.projet.repositories.AncrageVeloList;
import ca.uqam.projet.resources.AncrageVelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BDAncrageVelo extends BD {

    private static final String INSERT_ANCRAGE_VELO
            = "INSERT INTO ancragevelo(geog)"
            + "VALUES (ST_GeographyFromText('POINT(' || ? || ' ' || ? || ')'))"
            + "on conflict do nothing";

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
}
