package SIS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {

    public static Connection GetDatabaseConnection() {
        Connection connection = null;
        //    Connection connection;

        String dbUrl = "jdbc:mysql://localhost:3306/SIS";
        String user = "root";
        String pass = "Phobo$401647";
        try {
//            driver setup for database
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(dbUrl, user, pass);

//            System.out.println("connection successful");

        } catch (ClassNotFoundException e) {
            e.getLocalizedMessage();
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getLocalizedMessage();
        }

        return connection;
    }
}