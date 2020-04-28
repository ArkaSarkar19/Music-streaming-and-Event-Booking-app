package Ui.AddPlaylist;

import Core.UserPlaylist;
import Database.DBConnection;
import Database.DataController;
import Ui.MainPage.MainScreenController;
import Ui.SignupLoginMain.LoginBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Exception.CannotAddPlaylsitException;
import Exception.ConnectionInvalidException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddPlaylistController {
    public Button createButton;
    public TextField playlistName;
    public Button cancelButton;
    public static Stage addPlaylistwindow;
    public Scene scene;

    public void loadWindow() throws IOException {
        addPlaylistwindow = new Stage();
        Parent page = FXMLLoader.load(getClass().getResource("/Ui/AddPlaylist/AddPlaylist.fxml"));
        addPlaylistwindow.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(page);
        addPlaylistwindow.setScene(scene);

        addPlaylistwindow.setTitle("New Playlist");
        addPlaylistwindow.setResizable(false);
        addPlaylistwindow.show();
        cancelButton = (Button)scene.lookup("#cancelButton") ;
        cancelButton.setOnAction(actionEvent -> {
            addPlaylistwindow.close();
        });
        createButton = (Button)scene.lookup("#createButton");

    }

    public void handleCreateButton()  {
        try {
            int r = (int) ((Math.random() * 9000000) + 1000000);
            if(playlistName.getText().toString().equals("")) throw  new CannotAddPlaylsitException("Enter a playlist name !!!!");
            UserPlaylist userPlaylist = new UserPlaylist(MainScreenController.getUser().getUser_id(), r, playlistName.getText().toString(),0);
            DataController db = new DataController();
            db.addNewPlaylist(userPlaylist);
            addPlaylistwindow.close();
        }
        catch (CannotAddPlaylsitException e ){
            System.out.println(e.getMessage());
            System.out.println("Error while adding playlist");
            e.printStackTrace();
        }
        catch (ConnectionInvalidException e){
            System.out.println("Error while Establising connection");
            e.printStackTrace();
        }

    }



}
