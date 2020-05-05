package Ui.YourLibrary;

import Core.Advertisement;
import Core.UserPlaylist;
import Database.DBConnection;
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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Exception.*;

import javax.swing.text.html.ImageView;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class YourLibraryController {
    public Stage yourPlaylistsWindow;
    public Button playlistsButton;
    public Button artistsButton;
    public Button albumsButton;
    public AnchorPane activityWindow;
    public Button goHomeButton;
    public AnchorPane addWindow;
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

        addWindow = (AnchorPane) scene.lookup("#addWindow");
        int n = DataController.ads.size();
        Random rand = new Random();
        int r = rand.nextInt(n)-1;
        if(r<0) r = 0;
        Advertisement ad = DataController.ads.get(r);
        Button b2 =   new Button("Advertisement id : " +Integer.toString(ad.ad_id));
        Button b3 =   new Button("Advertiser id : " + Integer.toString(ad.advertiser_id));
        VBox vb = new VBox(30);
        vb.getChildren().add(b2);
        vb.getChildren().add(b3);

        addWindow.getChildren().add(vb);

    }

    public void handlePlaylistsButton(){
        DataController db = new DataController();

        try {
            ArrayList<UserPlaylist> list  = db.getAllPlaylists(MainScreenController.getUser());
            VBox vb = new VBox(15);
            HBox hbox =null;
            ArrayList<Button> buttonlist = new ArrayList<Button>();
            for(int i=0;i<list.size();i++){
                if(i%6 == 0){
                    hbox = new HBox(25);
                    hbox.setId("box" + i);
                    vb.getChildren().add(hbox);
                }
                list.get(i).display();
                Button b = new Button(list.get(i).getName());
                b.setId("playlistButton"+ i);
                UserPlaylist p = list.get(i);
                b.setOnAction(actionEvent -> {
                    PlayerController pc = new PlayerController();
                    pc.play_playlist(p.getPlaylist_id());
                });
                hbox.getChildren().add(b);
            }


            activityWindow.getChildren().add(vb);

        } catch (ConnectionInvalidException e) {
            e.printStackTrace();
        } catch (CannotAddPlaylsitException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleArtistsButton(){
        DataController db = new DataController();
        activityWindow.getChildren().clear();
        try {
                ArrayList<String> list = db.getArtistsUser();
            VBox vb = new VBox(15);
            HBox hbox =null;
            ArrayList<Button> buttonlist = new ArrayList<Button>();
            for(int i=0;i<list.size();i++){
                if(i%6 == 0){
                    hbox = new HBox(25);
                    hbox.setId("box" + i);
                    vb.getChildren().add(hbox);
                }
                Button b = new Button(list.get(i));
//                b.setId("playlistButton"+ i);
//                UserPlaylist p = list.get(i);
//                b.setOnAction(actionEvent -> {
//                    PlayerController pc = new PlayerController();
//                    pc.play_playlist(102);
//                });
                hbox.getChildren().add(b);
            }


            activityWindow.getChildren().add(vb);
        } catch (ConnectionInvalidException e) {
            e.printStackTrace();
        }

    }
    public void handleAlbumsButton(){
        DataController db = new DataController();
        activityWindow.getChildren().clear();
        try {
            ArrayList<String> list = db.getALbumsUser();
            VBox vb = new VBox(15);
            HBox hbox =null;
            ArrayList<Button> buttonlist = new ArrayList<Button>();
            for(int i=0;i<list.size();i++){
                if(i%6 == 0){
                    hbox = new HBox(25);
                    hbox.setId("box" + i);
                    vb.getChildren().add(hbox);
                }
                Button b = new Button(list.get(i));
//                b.setId("playlistButton"+ i);
//                UserPlaylist p = list.get(i);
//                b.setOnAction(actionEvent -> {
//                    PlayerController pc = new PlayerController();
//                    pc.play_playlist(102);
//                });
                hbox.getChildren().add(b);
            }


            activityWindow.getChildren().add(vb);
        } catch (ConnectionInvalidException e) {
            e.printStackTrace();
        }

    }
}



