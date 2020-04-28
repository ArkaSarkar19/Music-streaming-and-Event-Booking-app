package Ui.SearchFilter;

import Ui.Search.SearchPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchFilterController {
    public Stage searchFilterPage;
    public Scene scene;
    public Button cancelButton;

    public Button songsButton;
    public Button genresButton;
    public Button artistsButton;
    public Button playlistsButton;
    public String filter;

    public String loadWindow(){


        try {
            searchFilterPage = new Stage();
            Parent profile = FXMLLoader.load(SearchFilterController.class.getResource("SearchFilterPage.fxml"));
            searchFilterPage.initModality(Modality.APPLICATION_MODAL);
            scene = new Scene(profile);
            searchFilterPage.setScene(scene);
            searchFilterPage.setTitle("Search Filter");
            searchFilterPage.setResizable(false);
            searchFilterPage.show();

            cancelButton = (Button)scene.lookup("#cancelButton") ;
            cancelButton.setOnAction(actionEvent -> {

                searchFilterPage.close();
            });

            songsButton = (Button)scene.lookup("#songsButton") ;
            songsButton.setOnAction(actionEvent -> {
                filter = "songs";
                SearchPageController.SearchFilterString="songs";
                searchFilterPage.close();
            });

            genresButton = (Button)scene.lookup("#genresButton") ;
            genresButton.setOnAction(actionEvent -> {
                filter = "genres";
                SearchPageController.SearchFilterString="genres";
                searchFilterPage.close();
            });

            artistsButton = (Button)scene.lookup("#artistsButton") ;
            artistsButton.setOnAction(actionEvent -> {
                filter = "artists";
                SearchPageController.SearchFilterString="artists";
                searchFilterPage.close();
            });

            playlistsButton = (Button)scene.lookup("#playlistsButton") ;
            playlistsButton.setOnAction(actionEvent -> {
                filter = "playlists";
                SearchPageController.SearchFilterString="playlists";
                searchFilterPage.close();
            });



        }
        catch (Exception e){
            System.out.println("Error Loading Search Filter screen");
        }

        return filter;
    }
}
