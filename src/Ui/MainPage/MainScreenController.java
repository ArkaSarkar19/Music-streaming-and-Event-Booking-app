package Ui.MainPage;

import Core.SaveFileCache;
import Core.User;
import Ui.ProfilePage.ProfilePageController;
import Ui.SignupLoginMain.LoginBox;
import Ui.SignupLoginMain.Main;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Exception.SaveStateException;
import java.io.IOException;
import java.io.Serializable;

public class MainScreenController implements Serializable {
    public Stage mainPageWindow;
    public Scene scene;
    public Button userProfileButton;
    public ImageView userProfilePic;
    public Button homeButton;
    public Button searchButton;
    public Button yourLibraryButton;
    public Button addPlaylistButton;
    public Button yourPlaylistsButton;
    private User user;
    private SaveFileCache data;

    public  void loadWindow(User user, String password){
        try {
            this.user = user;
            data = new SaveFileCache(user.getEmail(),password);
            mainPageWindow = new Stage();
            Main.window.close();
            Parent mainPage = FXMLLoader.load(LoginBox.class.getResource("/Ui/MainPage/MainScreen.fxml"));
            mainPageWindow.initModality(Modality.APPLICATION_MODAL);
            scene = new Scene(mainPage);
            mainPageWindow.setScene(scene);
            mainPageWindow.setTitle("Profile");
            mainPageWindow.setResizable(false);
            mainPageWindow.show();
            mainPageWindow.setOnCloseRequest(e->{
                try {
                    throw new SaveStateException();
                }
                catch (SaveStateException e1) {
                    try {
                        e1.save(data);
                        System.out.println("Exiting");
                    }
                    catch (IOException ioException) {
                        System.out.println("Error during saving state");
                        ioException.printStackTrace();
                    }
                    mainPageWindow.close();
                }
            });
        }
        catch (Exception e){
            System.out.println("Error while loading main page");
            e.printStackTrace();
        }
        userProfileButton = (Button)scene.lookup("#userProfileButton");
        userProfilePic = (ImageView)scene.lookup("#userProfilePic");
        homeButton = (Button)scene.lookup("#homeButton");
        searchButton = (Button)scene.lookup("#searchButton");
        yourLibraryButton = (Button)scene.lookup("#yourLibraryButton");
        addPlaylistButton = (Button)scene.lookup("#addPlaylistButton");
        yourPlaylistsButton = (Button)scene.lookup("#yourPlaylistsButton");
        setCredentials();


    }
    public void handleUserProfileButton()  {
        ProfilePageController.loadWindow(user);
    }
    private void setCredentials(){
        userProfileButton.setText(user.getName());
    }
}
