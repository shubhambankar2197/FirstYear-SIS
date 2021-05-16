package SIS;

import java.io.IOException;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class AttendanceQuery implements Initializable {
    @FXML
    private Button back;
    @FXML
    private Button viewOverall;
    @FXML
    private Button viewSubWise;

    @FXML
    private TableView<AttendanceData> subWiseTable;
    @FXML
    private TableColumn<AttendanceData, String> id;
    @FXML
    private TableColumn<AttendanceData, String> sub1;
    @FXML
    private TableColumn<AttendanceData, String> sub2;
    @FXML
    private TableColumn<AttendanceData, String> sub3;
     @FXML
     private TableColumn<AttendanceData, String> sub4;
    private ObservableList<AttendanceData> data;
    @FXML
    private TextArea totalAttendance;

    private String username = LoginController.stuuname;

    public void back(ActionEvent actionEvent) throws IOException {
        back.getScene().getWindow().hide();
        Stage dashboardStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentDetails.fxml"));
        dashboardStage.setTitle("Student Details");

        Scene scene = new Scene(root);
        dashboardStage.setScene(scene);
        dashboardStage.show();

    }
    public void subWise(ActionEvent actionEvent) {
        Connection conn = DatabaseController.GetDatabaseConnection();
        this.data = FXCollections.observableArrayList();
        String checkQuery = "select *from stuSubAttendanceDetails where id = ? ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(checkQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                this.data.add(new AttendanceData(resultSet.getString("subject1"),resultSet.getString("subject2"),resultSet.getString("subject3"),resultSet.getString("subject4")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.sub1.setCellValueFactory(new PropertyValueFactory<AttendanceData, String>("sub1"));
        this.sub2.setCellValueFactory(new PropertyValueFactory<AttendanceData, String>("sub2"));
        this.sub3.setCellValueFactory(new PropertyValueFactory<AttendanceData, String>("sub3"));
        this.sub4.setCellValueFactory(new PropertyValueFactory<AttendanceData, String>("sub4"));
        this.subWiseTable.setItems(null);
        this.subWiseTable.setItems(this.data);
    }
        public void viewOverall(ActionEvent actionEvent) {
            Connection conn = DatabaseController.GetDatabaseConnection();
            String checkQuery2 = "SELECT sum(subject1 + subject2 + subject3 + subject4) as total from stuSubAttendanceDetails where id= ?";
            PreparedStatement preparedStatement2 = null;
            try {
                preparedStatement2 = conn.prepareStatement(checkQuery2);
                preparedStatement2.setString(1, username);
                ResultSet resultSet = preparedStatement2.executeQuery();
                System.out.println(resultSet);

                while(resultSet.next()) {
                    int result = resultSet.getInt(1);
                    System.out.println(result);
                    totalAttendance.setText(String.valueOf(result));
//                showTotalTextField.setText(resultSet.getString(total));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
