package EmpAllDetails;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection con = null;

    private DBConnection() {
    }

    static {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/kavish",   // database URL (replace samp with your DB name)
                "root",                               // MySQL username
                "Barath1425"                           // MySQL password
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getCon() {
        return con;
    }
}
