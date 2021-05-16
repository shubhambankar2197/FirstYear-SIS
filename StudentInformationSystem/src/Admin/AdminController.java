package Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    @FXML
    private Button addingStuInfo;
    @FXML
    private Button updateStuInfo;
    @FXML
    private Button deleteStuInfo;
    @FXML
    private Button insertStaffInfo;
    @FXML
    private Button logout;
    public void addStuInfo(ActionEvent actionEvent) throws IOException {
        addingStuInfo.getScene().getWindow().hide();
        Stage dashboardStage = new Stage();
        dashboardStage.setTitle("Adding a new Student Info");

        Parent root = FXMLLoader.load(getClass().getResource("studentPersonalInfo.fxml"));
        Scene scene = new Scene(root);
        dashboardStage.setScene(scene);
        dashboardStage.show();
    }

    public void updateStuInfo(ActionEvent actionEvent) throws IOException {
        updateStuInfo.getScene().getWindow().hide();
        Stage dashboardStage = new Stage();
        dashboardStage.setTitle("Updating Student Info");

        Parent root = FXMLLoader.load(getClass().getResource("updateStudentInfo.fxml"));
        Scene scene = new Scene(root);
        dashboardStage.setScene(scene);
        dashboardStage.show();
    }

    public void deleteStudentData(ActionEvent actionEvent) throws IOException {
        deleteStuInfo.getScene().getWindow().hide();
        Stage dashboardStage = new Stage();
        dashboardStage.setTitle("Dropping Student Info");

        Parent root = FXMLLoader.load(getClass().getResource("deleteStudentData.fxml"));
        Scene scene = new Scene(root);
        dashboardStage.setScene(scene);
        dashboardStage.show();
    }

    public void addStaffInfo(ActionEvent actionEvent) throws IOException {
        insertStaffInfo.getScene().getWindow().hide();
        Stage dashboardStage = new Stage();
        dashboardStage.setTitle("Adding Staff Info");

        Parent root = FXMLLoader.load(getClass().getResource("AddingStaffDetails.fxml"));
        Scene scene = new Scene(root);
        dashboardStage.setScene(scene);
        dashboardStage.show();
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        logout.getScene().getWindow().hide();
        Stage dashboardStage = new Stage();
        dashboardStage.setTitle("Admin Controller");

        Parent root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        Scene scene = new Scene(root);
        dashboardStage.setScene(scene);
        dashboardStage.show();
    }
}
