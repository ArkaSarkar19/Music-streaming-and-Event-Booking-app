package Ui.YourLibrary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class YourLibraryController {
    public Stage yourPlaylistsWindow;
    public Button playlistsButton;
    public Button artistsButton;
    public Button albumsButton;
    public Button goHomeButton;
    public Scene scene;

    public void loadwindow() throws IOException {
        yourPlaylistsWindow = new Stage();
        Parent page = FXMLLoader.load(getClass().getResource("/Ui/YourLibrary/YourLibrary.fxml"));
        yourPlaylistsWindow.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(page);
        yourPlaylistsWindow.setScene(scene);
        yourPlaylistsWindow.setTitle("New Playlist");
        yourPlaylistsWindow.setResizable(false);
        yourPlaylistsWindow.show();
        goHomeButton = (Button)scene.lookup("#goHomeButton") ;
        goHomeButton.setOnAction(actionEvent -> {
            yourPlaylistsWindow.close();
        });

    }

    public void handlePlaylistsButton(){

    }



}
