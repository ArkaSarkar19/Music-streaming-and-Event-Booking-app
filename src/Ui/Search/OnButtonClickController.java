package Ui.Search;

import Core.User;
import Database.DataController;
import Ui.Player.PlayerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class OnButtonClickController {
    public Stage stage;
    public Scene scene;

    public Label songNameLabel;
    public Button addToPlaylistButton;
    public Button playSongButton;

    public int songID;

    public void loadWindow(String buttonText, User user){

        try {
            stage = new Stage();
            Parent profile = FXMLLoader.load(OnButtonClickController.class.getResource("OnButtonClick.fxml"));
            stage.initModality(Modality.APPLICATION_MODAL);
            scene = new Scene(profile);
            stage.setScene(scene);
            stage.setTitle("Browse");
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        DataController dc = new DataController();
        try {
            songID = dc.getSongID(buttonText);
            System.out.println(songID);
        }
        catch (Exception e){
            System.out.println("Error");
        }


        songNameLabel = (Label) scene.lookup("#songNameLabel") ;
        songNameLabel.setText(buttonText);

        playSongButton = (Button)scene.lookup("#playSongButton") ;
        playSongButton.setOnAction(actionEvent -> {

            System.out.println("hello2");


            PlayerController pc = new PlayerController();
            pc.play_single_song(songID);

        });

        addToPlaylistButton = (Button)scene.lookup("#addToPlaylistButton") ;
        addToPlaylistButton.setOnAction(actionEvent -> {
            //write the code for add to playlist here.
            //buttonText -> song name
            System.out.println("hello1");

            AllPlaylistsController apc = new AllPlaylistsController();
            apc.loadWindow(user,songID);

        });

    }
}
