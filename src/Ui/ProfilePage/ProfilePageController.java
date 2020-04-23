package Ui.ProfilePage;

import Core.User;
import Ui.SignupLoginMain.LoginBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfilePageController {
    public static Stage profilePage;

    @FXML
    public Label profileUserAge;
    @FXML
    public ImageView profilePic;

    public static Scene scene;
    private User user;
    public  void loadWindow(User user){
        try {
            this.user = user;
            profilePage = new Stage();
            profilePage.initModality(Modality.APPLICATION_MODAL);
            Parent profile = FXMLLoader.load(ProfilePageController.class.getResource("ProfilePage.fxml"));
            scene = new Scene(profile);
            profilePage.setScene(scene);
            profilePage.setTitle("Profile");
            profilePage.setResizable(false);
            profilePage.show();

        }
        catch (IOException e){
            System.out.println("Error while opening profile page");
            e.printStackTrace();
        }

//        user.display();
        profileUserAge = (Label)scene.lookup("#profileUserAge");
        System.out.println(user.getName());
        System.out.println(user.getAge());
        profileUserAge.setText(user.getName());


    }



}
