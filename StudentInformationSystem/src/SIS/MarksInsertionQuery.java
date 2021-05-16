package SIS;

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

public class MarksInsertionQuery {
    @FXML
    private Button logout;
    @FXML
    private Button back;
    @FXML
    private TextField id;
    @FXML
    private TextField marks;
    @FXML
    private Button insert;
    String username = LoginController.staffuname;
    String subName;

    public void insert(ActionEvent actionEvent) {
        Connection connection = DatabaseController.GetDatabaseConnection();
        String checkQuery = "select subject from staffDetails where id = ? ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                subName = resultSet.getString("subject");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(subName);
        if(!subName.contains("placements")) {
            String stuID = id.getText();
            String subMarks = marks.getText();
            System.out.println(stuID+" "+subMarks);
            if(!subMarks.isEmpty()&&!stuID.isEmpty()) {
                Connection conn = DatabaseController.GetDatabaseConnection();
                //NSERT INTO Student(ROLL_NO,NAME,Age) SELECT ROLL_NO, NAME, Age FROM LateralStudent;
                String checkUpdateQuery = "update stuMarks set "+subName+" = ? where id = ?";
                PreparedStatement prepareStatement = null;
                try {
                    prepareStatement = conn.prepareStatement(checkUpdateQuery);
                    prepareStatement.setString(1, subMarks); // sending password to checkquery statement
                    prepareStatement.setString(2, stuID);
                    int resultSet = prepareStatement.executeUpdate();
                    id.setText(" ");
                    marks.setText(" ");

                } catch (SQLException e) {
                    e.printStackTrace();
                }



            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("Fields cannot be empty!!");
                alert.show();
            }



        }

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

    public void back(ActionEvent actionEvent) throws IOException {
        back.getScene().getWindow().hide();
        Stage dashboardStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("staffDetails.fxml"));
        dashboardStage.setTitle("Student Details");

        Scene scene = new Scene(root);
        dashboardStage.setScene(scene);
        dashboardStage.show();
    }
}



