package Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Welcome TO School Name");
        primaryStage.setScene(new Scene(root, 855, 700));
        primaryStage.setResizable(false);


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
