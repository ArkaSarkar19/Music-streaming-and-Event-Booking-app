package sample;

import javafx.animation.Animation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

//import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.io.IOException;
import java.nio.file.Paths;

public class LoginBox {
    public static Stage loginwindow;
    public static Stage signupwindow;


    public static void getLogin() throws IOException {
        loginwindow = new Stage();
        loginwindow.initModality(Modality.APPLICATION_MODAL);
        Parent login = FXMLLoader.load(LoginBox.class.getResource("LoginBox.fxml"));
        Scene scene1 = new Scene(login, 600,300);
//        Button logincancel = (Button)scene1.lookup("#loginCancelButton");
//        logincancel.setOnAction(event -> loginwindow.close());

        loginwindow.setScene(scene1);
        loginwindow.setTitle("Login");
        loginwindow.setResizable(false);
        loginwindow.showAndWait();
    }
}
