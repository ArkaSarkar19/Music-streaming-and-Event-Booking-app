package Ui.ProfilePage;

import Core.User;
import Ui.SignupLoginMain.LoginBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfilePageController {
    public static Stage profilePage;

    public static void loadWindow(User user){
        try {
            profilePage = new Stage();
            profilePage.initModality(Modality.APPLICATION_MODAL);
            Parent profile = FXMLLoader.load(ProfilePageController.class.getResource("ProfilePage.fxml"));
            Scene scene1 = new Scene(profile);
            profilePage.setScene(scene1);
            profilePage.setTitle("Profile");
            profilePage.setResizable(false);
            profilePage.showAndWait();
        }
        catch (IOException e){
            System.out.println("Error while opening profile page");
            e.printStackTrace();
        }
    }
}
