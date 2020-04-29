package Ui.YourLibrary;

import Core.UserPlaylist;
import Database.DataController;
import Ui.MainPage.MainScreenController;
import Ui.Player.PlayerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Exception.*;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.util.ArrayList;

public class YourLibraryController {
    public Stage yourPlaylistsWindow;
    public Button playlistsButton;
    public Button artistsButton;
    public Button albumsButton;
    public AnchorPane activityWindow;
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
        Button b = (Button)scene.lookup("#userProfileButton");
        b.setText(MainScreenController.getUser().getName());

    }

    public void handlePlaylistsButton(){
        DataController db = new DataController();

        try {
            ArrayList<UserPlaylist> list  = db.getAllPlaylists(MainScreenController.getUser());
            HBox hbox = new HBox(25);
            ArrayList<Button> buttonlist = new ArrayList<Button>();
            for(int i=0;i<list.size();i++){
                list.get(i).display();
                Button b = new Button(list.get(i).getName());
                b.setId("playlistButton"+ i);
                UserPlaylist p = list.get(i);
                b.setOnAction(actionEvent -> {
                    PlayerController pc = new PlayerController();
                    pc.play_playlist(102);
                });
                buttonlist.add(b);
            }

            hbox.getChildren().addAll(buttonlist);

            activityWindow.getChildren().add(hbox);

        } catch (ConnectionInvalidException e) {
            e.printStackTrace();
        } catch (CannotAddPlaylsitException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleArtistsButton(){
        DataController db = new DataController();

        try {
            ArrayList<UserPlaylist> list  = db.getAllPlaylists(MainScreenController.getUser());

        } catch (ConnectionInvalidException e) {
            e.printStackTrace();
        } catch (CannotAddPlaylsitException e) {
            e.printStackTrace();
        }

    }
    public void handleAlbumsButton(){

    }



}
