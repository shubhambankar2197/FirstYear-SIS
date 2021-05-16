package SIS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public static String staffuname,staffpass;
    public static String stuuname, stupass;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button stulogin;
    @FXML
    private  Button stafflogin;




    /*  Staff Login */
    public void staffLogin(ActionEvent actionEvent) throws IOException {
        staffuname = username.getText();
        staffpass = password.getText();
        if (staffLoginQuery.CheckLoginUser(staffuname, staffpass)) { // sending data to databasehandler class to connection data
            stafflogin.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("Staff Details");

            Parent root = FXMLLoader.load(getClass().getResource("staffDetails.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        }
        else {
            alert();
        }

    }


    /*  Student Login */
    public void studentLogin() throws IOException {
        stuuname = username.getText();
        stupass = password.getText();


        if (studentLoginQuery.CheckLoginUser(stuuname, stupass)) { // sending data to databasehandler class to connection data
            stulogin.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("studentDetails.fxml"));
            dashboardStage.setTitle("Student Details");
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        }

    else{
            alert();
        }
    }



    private void alert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Username or Password Incorrect!!. Please enter Correctly.");
        alert.show();
//            reseting user and pass field
        username.setText("");
        password.setText("");
    }
}
