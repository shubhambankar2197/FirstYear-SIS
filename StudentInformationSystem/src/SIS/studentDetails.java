package SIS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class studentDetails extends LoginController{
    @FXML
    private Button attendanceDetails;

    @FXML
    private Button logout;
    @FXML
    private Button MarksDetails;
    @FXML
    TableView<StudentData> stuDetails;
    @FXML
    private TableColumn<StudentData, String> id;
    @FXML
    private TableColumn<StudentData, String> name;
    @FXML
    private TableColumn<StudentData, String> department;
    @FXML
    private TableColumn<StudentData, String> year;
    private ObservableList<StudentData> data;
    private static String StudentUserName;


//    public  studentDetails() {
//        this.showdata();
//    }

    public void showdata () {
        StudentUserName = LoginController.stuuname;
        Connection conn = DatabaseController.GetDatabaseConnection();
        this.data = FXCollections.observableArrayList();
        String checkQuery1 = "select id,firstName,department,year from studentDetails where id = ? ";
        PreparedStatement preparedStatement1 = null;
        try {
            preparedStatement1 = conn.prepareStatement(checkQuery1);
            preparedStatement1.setString(1, StudentUserName);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                this.data.add(new StudentData(resultSet1.getString("id"), resultSet1.getString("firstName"), resultSet1.getString("department"), resultSet1.getString("year")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.id.setCellValueFactory(new PropertyValueFactory<StudentData,String>("id"));
        this.name.setCellValueFactory(new PropertyValueFactory<StudentData, String>("name"));
        this.department.setCellValueFactory(new PropertyValueFactory<StudentData, String>("department"));
        this.year.setCellValueFactory(new PropertyValueFactory<StudentData, String>("year"));
        this.stuDetails.setItems(null);
        this.stuDetails.setItems(this.data);
    }

    public void attendanceDetails(ActionEvent event) throws IOException, SQLException {

//            if (AttendanceQuery.CheckAttendance(username)) { // sending data to databasehandler class to connection data
//            AttendanceQuery.CheckAttendance(username);
            attendanceDetails.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ViewAtendance.fxml"));
            dashboardStage.setTitle("Attendance Info");

            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
//        } else {
//            alert();
//        }

    }

    private void alert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Please enter Correctly.");
        alert.show();
//            reseting user and pass field

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

    public void marksDetails(ActionEvent actionEvent) throws IOException {
        MarksDetails.getScene().getWindow().hide();
        Stage dashboardStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MarksDetails.fxml"));
        dashboardStage.setTitle("Student Report Card");

        Scene scene = new Scene(root);
        dashboardStage.setScene(scene);
        dashboardStage.show();
    }

    public void eligibilityDetails(ActionEvent actionEvent) {
    }
}
