package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Martex on 6/9/2017.
 */
public class dbconnect {
    private static final String SQCONN ="jdbc:sqlite:school.sqlite";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQCONN);
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        }
        return null;
    }
}
