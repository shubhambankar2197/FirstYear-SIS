package SIS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarksDetails {
    @FXML
    private Button back;
    @FXML
    private TableView<MarksData> reportCard;
    private ObservableList<MarksData> data;
    @FXML
    private TableColumn<MarksData, String> subject1;
    @FXML
    private TableColumn<MarksData, String> subject2;
    @FXML
    private TableColumn<MarksData, String> subject3;
    @FXML
    private TableColumn<MarksData, String> subject4;
    String StudentUserName;
    @FXML
    private TextArea showTotalTextField;
    @FXML
    private TextArea showPerTextField;

   public void reportCard(MouseEvent mouseEvent) {
        StudentUserName = LoginController.stuuname;
        Connection conn = DatabaseController.GetDatabaseConnection();
        this.data = FXCollections.observableArrayList();
        String checkQuery1 = "select subject1,subject2,subject3,subject4 from stuMarks where id = ? ";
        PreparedStatement preparedStatement1 = null;
        try {
            preparedStatement1 = conn.prepareStatement(checkQuery1);
            preparedStatement1.setString(1, StudentUserName);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                this.data.add(new MarksData(resultSet1.getString("subject1"), resultSet1.getString("subject2"), resultSet1.getString("subject3"), resultSet1.getString("subject4")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.subject1.setCellValueFactory(new PropertyValueFactory<MarksData, String>("subject1"));
        this.subject2.setCellValueFactory(new PropertyValueFactory<MarksData, String>("subject2"));
        this.subject3.setCellValueFactory(new PropertyValueFactory<MarksData, String>("subject3"));
        this.subject4.setCellValueFactory(new PropertyValueFactory<MarksData, String>("subject4"));
        this.reportCard.setItems(null);
        this.reportCard.setItems(this.data);
    }

    public void showTotal(ActionEvent actionEvent) {
        Connection conn = DatabaseController.GetDatabaseConnection();
        String checkQuery2 = "SELECT sum(subject1 + subject2 + subject3 + subject4) as total from stuMarks where id= ?";
        PreparedStatement preparedStatement2 = null;
        try {
            preparedStatement2 = conn.prepareStatement(checkQuery2);
            preparedStatement2.setString(1, StudentUserName);
            ResultSet resultSet = preparedStatement2.executeQuery();
            System.out.println(resultSet);

            while(resultSet.next()) {
                int result = resultSet.getInt(1);
                System.out.println(result);
                showTotalTextField.setText(String.valueOf(result));
//                showTotalTextField.setText(resultSet.getString(total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void showPer(ActionEvent actionEvent) {
        Connection conn = DatabaseController.GetDatabaseConnection();
        String checkQuery3 = "SELECT (((subject1 + subject2 + subject3 + subject4)/400)*100) as percentage from stuMarks where id= ?";
        PreparedStatement preparedStatement3 = null;
        try {
            preparedStatement3 = conn.prepareStatement(checkQuery3);
            preparedStatement3.setString(1, StudentUserName);
            ResultSet resultSet = preparedStatement3.executeQuery();
            System.out.println(resultSet);

            while(resultSet.next()) {
                int result = resultSet.getInt(1);
                System.out.println(result);
                showPerTextField.setText(String.valueOf(result));
//                showTotalTextField.setText(resultSet.getString(total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void back(ActionEvent actionEvent) throws IOException {
        back.getScene().getWindow().hide();
        Stage dashboardStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("studentDetails.fxml"));
        dashboardStage.setTitle("Student Details");

        Scene scene = new Scene(root);
        dashboardStage.setScene(scene);
        dashboardStage.show();
    }
}
