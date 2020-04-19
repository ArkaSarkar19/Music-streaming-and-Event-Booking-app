package Ui.SignupLoginMain;
import Exception.*;
import Database.DataController;
import javafx.animation.Animation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        Button loginbutton = (Button)scene1.lookup("#loginbutton");
        loginbutton.setOnAction(event -> {
            TextField user = (TextField)scene1.lookup("#loginusername");
            PasswordField password = (PasswordField)scene1.lookup("#loginpassword");
            try{
                DataController cont = new DataController();
                cont.validateLogin(user.getText(),password.getText());
                System.out.println("SuccessFull");
                loginwindow.close();

            } catch (MyException e) {
                System.out.println("Error occured during login");
                e.printStackTrace();
            }
        });

        loginwindow.setScene(scene1);
        loginwindow.setTitle("Login");
        loginwindow.setResizable(false);
        loginwindow.showAndWait();
    }
    public static void getSignup() throws IOException {
        signupwindow = new Stage();
        signupwindow.initModality(Modality.APPLICATION_MODAL);
        Parent login = FXMLLoader.load(LoginBox.class.getResource("Signup.fxml"));
        ToggleGroup group = new ToggleGroup();
        Scene scene1 = new Scene(login);
        RadioButton m = (RadioButton)scene1.lookup("#gendermale");
        RadioButton f = (RadioButton)scene1.lookup("#genderfemale");
        RadioButton o = (RadioButton)scene1.lookup("#genderother");
        m.setToggleGroup(group);
        f.setToggleGroup(group);
        o.setToggleGroup(group);
        group.getSelectedToggle();
        signupwindow.setScene(scene1);
        signupwindow.setTitle("Register");
        signupwindow.setResizable(false);
        signupwindow.show();
    }
}
