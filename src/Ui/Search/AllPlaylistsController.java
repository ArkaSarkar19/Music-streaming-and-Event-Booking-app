package Ui.Search;

import Core.User;
import Core.UserPlaylist;
import Database.DataController;
import Ui.MainPage.MainScreenController;
import Ui.Player.PlayerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;

//import java.awt.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;

public class AllPlaylistsController {
    public Stage stage;
    public Scene scene;

    public Label doneText;
    public VBox vbox;
    public int song_id;

    public void loadWindow(User user, int  song_id){
        try {
            stage = new Stage();
            Parent profile = FXMLLoader.load(AllPlaylistsController.class.getResource("AllPlaylists.fxml"));
            stage.initModality(Modality.APPLICATION_MODAL);
            scene = new Scene(profile);
            stage.setScene(scene);
            stage.setTitle("All Playlists");
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        ArrayList<Button> buttonsList = new ArrayList<Button>();

        vbox = (VBox) scene.lookup("#vbox");

        DataController db = new DataController();
        try{
            ArrayList<UserPlaylist> list  = db.getAllPlaylists(MainScreenController.getUser());
            for (int i=0;i<list.size();i++){
                Button b = new Button(list.get(i).getName());
                buttonsList.add(b);

                addToPlaylist(list.get(i).getPlaylist_id(),song_id,b);
            }

            vbox.getChildren().addAll(buttonsList);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        buttonsList.clear();

    }

    public void addToPlaylist(int playlist_id, int song_id, Button b){
        b.setOnAction(actionEvent -> {
            DataController dc = new DataController();
            try {
                dc.addToPlaylist(playlist_id,song_id);

                doneText = (Label) scene.lookup("#doneText");
                doneText.setText("Added! to the playlist");
            }
            catch (Exception e){
                e.printStackTrace();
            }

        });

    }
}
