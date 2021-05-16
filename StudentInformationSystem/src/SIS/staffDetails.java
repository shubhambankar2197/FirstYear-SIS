package SIS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class staffDetails{

        @FXML
        private Button addStudentAttendance;
        @FXML
        private Button logout;

        private String username = LoginController.staffuname;
        public String desig;

        public void addStuAttendance(ActionEvent actionEvent) throws IOException {

            Connection connection = DatabaseController.GetDatabaseConnection();
            String checkQuery = "select designation from staffDetails where id = ? ";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(checkQuery);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    desig = resultSet.getString("designation");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(desig);
                if(desig.contains("teacher")) {
                    addStudentAttendance.getScene().getWindow().hide();
                    Stage dashboardStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AttendanceInsertionForm.fxml"));
                    dashboardStage.setTitle("Attendance Insertion Form");
                    Scene scene = new Scene(root);
                    dashboardStage.setScene(scene);
                    dashboardStage.show();
                }
                else  {
                    alert();
                }

            }

    private void alert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Access Denied");
        alert.show();
//            reseting user and pass field
    }



    public void addStuMarks(ActionEvent actionEvent) throws IOException {
        Connection connection = DatabaseController.GetDatabaseConnection();
        String checkQuery = "select designation from staffDetails where id = ? ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                desig = resultSet.getString("designation");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(desig);
        if(desig.contains("teacher")) {
            addStudentAttendance.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("MarksInsertionForm.fxml"));
            dashboardStage.setTitle("Marks Insertion Form");
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        }
        else  {
            alert();
        }

    }




    public void StaffInfo(ActionEvent actionEvent) {
    }

    public void viewStuDetails(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        logout.getScene().getWindow().hide();
        Stage dashboardStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        dashboardStage.setTitle("Student Details");

        Scene scene = new Scene(root);
        dashboardStage.setScene(scene);
        dashboardStage.show();
    }

    public void createEliList(ActionEvent actionEvent) {
    }
}
