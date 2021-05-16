package Admin;

import SIS.DatabaseController;
import SIS.staffLoginQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginController {
    @FXML
    private TextField adminUsername;
    @FXML
    private TextField adminPassword;
    @FXML
    private Button adminlogin;
    public String aUsername, aPassword;



    public void adminLogin(ActionEvent actionEvent) throws IOException {
        aUsername = adminUsername.getText();
        aPassword = adminPassword.getText();
        if (CheckLoginUser(aUsername, aPassword)) { // sending data to databasehandler class to connection data
            adminlogin.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("Admin Controller");

            Parent root = FXMLLoader.load(getClass().getResource("adminControls.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Username or Password Incorrect!!");
            alert.show();
//            reseting user and pass field
            adminUsername.setText("");
            adminPassword.setText("");
        }
    }

    private boolean CheckLoginUser(String aUsername, String aPassword) {
        Connection connection = SIS.DatabaseController.GetDatabaseConnection();
//        String checkQuery = "select * from loginDetails where user = '"+uname+"' and pass = '"+pass+"' ";
        String checkQuery = "select *from adminDetails where adminId = ? and adminPassword = ? "; // i don't use id from database table.

        PreparedStatement preparedStatement = null;
        boolean status = false; //initially false

        try {
            preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, aUsername); // dynamically sending username
            preparedStatement.setString(2, aPassword); // sending password to checkquery statement
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
