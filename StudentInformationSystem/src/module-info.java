module StudentInformationSystem {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires mysql.connector.java;
    opens SIS;
    opens Admin;
}