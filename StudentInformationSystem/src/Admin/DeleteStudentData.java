package Admin;

import SIS.DatabaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DeleteStudentData extends AdminLoginController {
    @FXML
    private Button dropButton;
    @FXML
    private TextField stuIdforDrop;
    @FXML
    private PasswordField AdminPassword;
    String APass;
    String AID;

    public void dropEntry(ActionEvent actionEvent) {
        Connection connection = SIS.DatabaseController.GetDatabaseConnection();
//        String checkQuery = "select * from loginDetails where user = '"+uname+"' and pass = '"+pass+"' ";
        String checkQuery = "delete from studentDetails where id = ? "; // i don't use id from database table.

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, stuIdforDrop.getText()); // dynamically sending username
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("WARNING");
            alert.setContentText("Do you really want to delete the data??");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {


//                TextInputDialog dialog = new TextInputDialog("");
//                dialog.setTitle("Text Input Dialog");
//                dialog.setHeaderText("Look, a Text Input Dialog");
//                dialog.setContentText("Please enter your name:");
//                Optional<String> result5 = dialog.showAndWait();
//                if (result5.isPresent()) {
//                    System.out.println("Your name: " + result5.get());
//                }
                preparedStatement.executeUpdate();
                preparedStatement.close();
                dropButton.getScene().getWindow().hide();
                Stage dashboardStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
                dashboardStage.setTitle("Student Information System - Admin Panel");

                Scene scene = new Scene(root);
                dashboardStage.setScene(scene);
                dashboardStage.show();
            } else {

            }


        } catch (SQLException | IOException e) {
//            e.getLocalizedMessage();
            e.printStackTrace();
        }
    }

    private void alert() {
    }

}