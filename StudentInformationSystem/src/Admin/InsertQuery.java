package Admin;

import SIS.DatabaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class InsertQuery  {
    @FXML
    TextField firstName;
    @FXML
    TextField middleName;
    @FXML
    TextField lastName;
    @FXML
    TextField stuID;
    @FXML
    TextField password;
    @FXML
    TextField email;
    @FXML
    ComboBox<String> department;
    @FXML
    ComboBox<String> year;
    @FXML
    RadioButton male;
    @FXML
    RadioButton female;
    @FXML
    DatePicker dob;
    @FXML
    TextField mobileNo;
    @FXML
    Button back;

    String sex,id;
    LocalDate ld;
    String regex = "^(.+)@(.+)$";
    public void insertQuery(ActionEvent actionEvent) {
        if(male.isSelected()) {
            sex = "male";
        }
        else if(female.isSelected()) {
            sex ="female";
        }
        else
            sex="";

        ld = dob.getValue();
//        if(String.valueOf(ld).contains(null)) {
//
//        }
//        else  {
            System.out.println(ld);
            id = stuID.getText();

        System.out.println(id);

            if(ld == null || id.equals("") || sex.equals("") || firstName.getText().equals("") ||middleName.getText().equals("")||
                    lastName.getText().equals("") || email.getText().equals("") || mobileNo.getText().equals("")||
                    department.getValue() == null || year.getValue() == null) {
                alert("Please fill all the fields correctly");
            }

            if(!email.getText().matches(regex)) {
                alert("Enter email correctly");
            }
            else {
                Connection connection = DatabaseController.GetDatabaseConnection();
                String checkQuery = "insert into studentDetails(firstName,middleName,lastName,id,password,gender,DOB,department,year,mobileNo,email) values (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = connection.prepareStatement(checkQuery);
                    preparedStatement.setString(1, firstName.getText().toUpperCase());
                    preparedStatement.setString(2, middleName.getText().toUpperCase());
                    preparedStatement.setString(3, lastName.getText().toUpperCase());
                    preparedStatement.setString(4, stuID.getText().toUpperCase());
                    preparedStatement.setString(5, password.getText().toLowerCase());
                    preparedStatement.setString(6, sex.toUpperCase());
                    preparedStatement.setString(7, String.valueOf(ld).toUpperCase());
                    preparedStatement.setString(8, department.getValue().toUpperCase());
                    preparedStatement.setString(9, year.getValue().toUpperCase());
                    preparedStatement.setString(10, mobileNo.getText().toUpperCase());
                    preparedStatement.setString(11, email.getText().toLowerCase());
                    int resultSet = preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String checkQuery1 = "insert into studentLogin(id,password) values (?,?)";
                PreparedStatement preparedStatement1 = null;
                try {
                    preparedStatement1 = connection.prepareStatement(checkQuery1);
                    preparedStatement1.setString(1, stuID.getText());
                    preparedStatement1.setString(2, password.getText());
                    int resultSet1 = preparedStatement1.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String checkQuery2 = "insert into stuMarks(id,subject1,subject2,subject3,subject4) values (?,?,?,?,?)";
                PreparedStatement preparedStatement2 = null;
                try {
                    preparedStatement2 = connection.prepareStatement(checkQuery2);
                    preparedStatement2.setString(1, stuID.getText());
                    preparedStatement2.setString(2, "0");
                    preparedStatement2.setString(3, "0");
                    preparedStatement2.setString(4, "0");
                    preparedStatement2.setString(5, "0");
                    int resultSet2 = preparedStatement2.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String checkQuery3 = "insert into stuSubAttendanceDetails(id,subject1,subject2,subject3,subject4) values (?,?,?,?,?)";
                PreparedStatement preparedStatement3 = null;
                try {
                    preparedStatement3 = connection.prepareStatement(checkQuery3);
                    preparedStatement3.setString(1, stuID.getText());
                    preparedStatement3.setString(2, "0");
                    preparedStatement3.setString(3, "0");
                    preparedStatement3.setString(4, "0");
                    preparedStatement3.setString(5, "0");
                    int resultSet3 = preparedStatement3.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                firstName.setText("");
                firstName.setPromptText("firstName");
                middleName.setText("");
                middleName.setPromptText("middleName");
                lastName.setText("");
                lastName.setPromptText("lastName");
                stuID.setText("");
                stuID.setPromptText("stuID");
                password.setText("");
                password.setPromptText("password");
                mobileNo.setText("");
                mobileNo.setPromptText("98xxxxxxx");
                email.setText("");
                email.setPromptText("abc@pqr.com");
                department.setValue("select department");
                year.setValue("select year");
                dob.disableProperty().setValue(null);
                if(male.isSelected()) {
                    male.selectedProperty().setValue(false);
                }
                else
                    female.selectedProperty().setValue(false);
            }
    }
    public void back(ActionEvent actionEvent) throws IOException {
        back.getScene().getWindow().hide();
        Stage dashboardStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("adminControls.fxml"));
        dashboardStage.setTitle("Student Details");

        Scene scene = new Scene(root);
        dashboardStage.setScene(scene);
        dashboardStage.show();
    }

    public void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.show();
    }

}
