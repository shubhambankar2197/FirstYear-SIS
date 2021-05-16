package SIS;

import java.sql.*;

public class staffLoginQuery extends DatabaseController{
    public static boolean CheckLoginUser(String uname, String pass) {
        System.out.println(uname+" "+pass);//get input from login system module
        Connection connection = GetDatabaseConnection();
//        String checkQuery = "select * from loginDetails where user = '"+uname+"' and pass = '"+pass+"' ";
        String checkQuery = "select *from staffDetails where id = ? and password = ? "; // i don't use id from database table.

        PreparedStatement preparedStatement = null;
        boolean status = false; //initially false

        try {
            preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, uname); // dynamically sending username
            preparedStatement.setString(2, pass); // sending password to checkquery statement
            ResultSet resultSet = preparedStatement.executeQuery();

//            while (resultSet.next()) {
//               return status;
//           }

            status = resultSet.next();
            preparedStatement.close();
            return status;

        } catch (SQLException e) {
//            e.getLocalizedMessage();
            e.printStackTrace();
        }
        return status;
    }
}
