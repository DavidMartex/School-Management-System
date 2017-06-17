package Login;

import admin.AdminController;
import clojure.lang.Delay;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class LoginController implements Initializable {

    LoginModel loginmodel= new LoginModel();

    @FXML
    private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Label loginstatus;
    @FXML
    private ImageView imgProgress;

    @Override
    public void initialize(URL url, ResourceBundle resources) {

        imgProgress.setVisible(false);

        if (loginmodel.isDatabaseConnected()){
            dbstatus.setText("DB Connected");
        }else{
            dbstatus.setText("DB Not Connected");
        }
    }

    @FXML
    public void login(ActionEvent e){
        imgProgress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev -> {
            completelogin();
        });
        pt.play();
    }
        public void completelogin() {
        try {
            if (this.loginmodel.isLogin(this.username.getText(), this.password.getText())) {


                Stage stage = (Stage) this.login.getScene().getWindow();
                stage.close();

                Stage adminstage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader();
                Pane root = (Pane) fxmlLoader.load(getClass().getResource("/admin/Dashboard.fxml").openStream());

                AdminController adminController = (AdminController) fxmlLoader.getController();

                Scene scene = new Scene(root);
                adminstage.setScene(scene);
                adminstage.setTitle("Dashboard");
                adminstage.show();
                adminstage.setMaximized(true);



                scene.getStylesheets().add("/admin/custom.css");

            } else {
                loginstatus.setText("Wrong Credentials. Try Again !");
                imgProgress.setVisible(false);

            }
        } catch (Exception e1) {

            e1.printStackTrace();
        }
        }

        }









