package Ui.MainPage;

import Core.User;
import Ui.ProfilePage.ProfilePageController;
import Ui.SignupLoginMain.LoginBox;
import Ui.SignupLoginMain.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenController {
    public Button userProfileButton;
    public ImageView userProfilePic;
    public Button homeButton;
    public Button searchButton;
    public Button yourLibraryButton;
    public Button addPlaylistButton;
    public Button yourPlaylistsButton;
    private User user;

    public static void loadWindow(String email, String Password){
        try {
            Parent mainPage = FXMLLoader.load(LoginBox.class.getResource("/Ui/MainPage/MainScreen.fxml"));
            Main.window.setScene(new Scene(mainPage));
        }
        catch (Exception e){
            System.out.println("Error while loading main page");
            e.printStackTrace();
        }

    }
    public void handleUserProfileButton()  {
        ProfilePageController.loadWindow(user);
    }
}
