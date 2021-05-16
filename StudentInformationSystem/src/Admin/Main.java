package Admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage adminStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("adminControls.fxml"));
        Parent admin = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        adminStage.setTitle("Student Information System - Admin Panel");
        adminStage.setScene(new Scene(admin, 640, 480));
        adminStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
