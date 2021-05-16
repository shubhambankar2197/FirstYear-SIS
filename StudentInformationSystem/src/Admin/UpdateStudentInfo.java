package Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateStudentInfo {
    @FXML
    private TextField updateStudentid;
    @FXML
    private TextField UpdatedfirstName;
    @FXML
    private TextField UpdatedmiddleName;
    @FXML
    private TextField UpdatedlastName;
    @FXML
    private TextField Updatedemail;
    @FXML
    private TextField UpdatedmobileNo;
    @FXML
    private DatePicker UpdatedDOB;




    @FXML
    private CheckBox fname;
    @FXML
    private CheckBox mname;
    @FXML
    private CheckBox lname;
    @FXML
    private CheckBox dob;
    @FXML
    private CheckBox mnumber;
    @FXML
    private CheckBox email;
    @FXML
    private Button updateInfo;
    String stuID,UfirstName, UlastName, UmiddleName, Uemail, UmNumber, Udob;
    String regex = "^(.+)@(.+)$";

    boolean firstname, lastname, mobileNumber, DOB, Email, middlename;
    LocalDate ld;

    public void UpdateDetails(ActionEvent actionEvent) {
        stuID = updateStudentid.getText();


        Connection connection = SIS.DatabaseController.GetDatabaseConnection();
        String checkQuery = "select id from studentDetails where id = ?"; // i don't use id from database table.

        PreparedStatement preparedStatement = null;
        boolean status = false; //initially false

        try{
        preparedStatement = connection.prepareStatement(checkQuery);
        preparedStatement.setString(1, stuID); // dynamically sending username
        ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String result = resultSet.getString(1);
                System.out.println(result);
                System.out.println(stuID);
                if(result.equals(stuID)) {


                    if(fname.isSelected()) {
                        firstname = true;
                        UfirstName = UpdatedfirstName.getText();
                        String checkQuery1 = "update studentDetails set firstName = ? where id = ?";
                        PreparedStatement preparedStatement1 = null;
                        try {
                            preparedStatement1 = connection.prepareStatement(checkQuery1);
                            preparedStatement1.setString(1, UfirstName.toUpperCase()); // dynamically sending username
                            preparedStatement1.setString(2, stuID); // dynamically sending username
                            preparedStatement1.executeUpdate();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                        if (mname.isSelected()) {
                            middlename = true;
                            UmiddleName = UpdatedmiddleName.getText();
                            String checkQuery2 = "update studentDetails set middleName = ? where id = ?";
                            PreparedStatement preparedStatement2 = null;
                            try {
                                preparedStatement2 = connection.prepareStatement(checkQuery2);
                                preparedStatement2.setString(1, UmiddleName.toUpperCase()); // dynamically sending username
                                preparedStatement2.setString(2, stuID); // dynamically sending username
                                preparedStatement2.executeUpdate();

                            } catch (SQLException e) {
                                e.printStackTrace();
                        }
                    }
                    if(lname.isSelected()) {
                        lastname = true;
                        UlastName = UpdatedlastName.getText();
                        String checkQuery3 = "update studentDetails set lastName = ? where id = ?";
                        PreparedStatement preparedStatement3 = null;
                        try {
                            preparedStatement3 = connection.prepareStatement(checkQuery3);
                            preparedStatement3.setString(1, UlastName.toUpperCase()); // dynamically sending username
                            preparedStatement3.setString(2, stuID); // dynamically sending username
                            preparedStatement3.executeUpdate();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if(dob.isSelected()) {
                        DOB = true;
                        ld = UpdatedDOB.getValue();
                        String checkQuery4 = "update studentDetails set DOB = ? where id = ?";
                        PreparedStatement preparedStatement4 = null;
                        try {
                            preparedStatement4 = connection.prepareStatement(checkQuery4);
                            preparedStatement4.setString(1, String.valueOf(ld).toUpperCase()); // dynamically sending username
                            preparedStatement4.setString(2, stuID); // dynamically sending username
                            preparedStatement4.executeUpdate();

                        } catch (SQLException e) {
                            e.printStackTrace();

                        }
                    }
                    if(mnumber.isSelected()) {
                        mobileNumber = true;
                        UmNumber = UpdatedmobileNo.getText();
                        String checkQuery5 = "update studentDetails set mobileNo = ? where id = ?";
                        PreparedStatement preparedStatement5 = null;
                        try {
                            preparedStatement5 = connection.prepareStatement(checkQuery5);
                            preparedStatement5.setString(1, UmNumber); // dynamically sending username
                            preparedStatement5.setString(2, stuID); // dynamically sending username
                            preparedStatement5.executeUpdate();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if(email.isSelected()) {
                        if (Updatedemail.getText().matches(regex)) {
                            Email = true;
                            Uemail = Updatedemail.getText();
                            String checkQuery6 = "update studentDetails set email = ? where id = ?";
                            PreparedStatement preparedStatement6 = null;
                            try {
                                preparedStatement6 = connection.prepareStatement(checkQuery6);
                                preparedStatement6.setString(1, Uemail.toLowerCase()); // dynamically sending username
                                preparedStatement6.setString(2, stuID); // dynamically sending username
                                preparedStatement6.executeUpdate();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        }
                        else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Error");
                            alert.setContentText("Enter email correctly");
                            alert.show();
                        }
                    }

//                    if((!fname.isSelected() && !UfirstName.isEmpty() )|| (!mname.isSelected() && !UmiddleName.isEmpty()) ||
//                            (!lname.isSelected() && !UlastName.isEmpty()) || (!dob.isSelected() && !ld.toString().isEmpty()) ||
//                            (!mnumber.isSelected() && !UmNumber.isEmpty()) ||( !email.isSelected() && !Uemail.isEmpty())) {
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Error");
//                        alert.setContentText("Select checkbox for updation");
//                        alert.show();
//
////                            || !lname.isSelected() || !dob.isSelected()|| mnumber.isSelected()|| email.isSelected()|| mnumber.isSelected()
//                    }







                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setContentText("StudentId incorrect");
                    alert.show();
//            reseting user and pass field
                    updateStudentid.setText("");

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
