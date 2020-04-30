package Ui.MainPage;

import Core.SaveFileCache;
import Core.User;
import Ui.AddPlaylist.AddPlaylistController;
import Ui.MyBookings.MyBookingsController;
import Ui.Player.PlayerController;
import Ui.ProfilePage.ProfilePageController;
import Ui.Search.SearchPageController;
import Ui.SignupLoginMain.LoginBox;
import Ui.SignupLoginMain.Main;
import Ui.YourLibrary.YourLibraryController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Exception.SaveStateException;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import Ui.Events.*;
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
    private static User user;
    private SaveFileCache data;

    @FXML
    ImageView a1, a2 ,a3 ,a4, a5, a6, g1, g2 ,g3 ,g4 ,g5, g6, s1 ,s2, s3, s4 ,s5 ,s6;

    public  void loadWindow(User user, String password){
        this.user = user;
        try {
            data = new SaveFileCache(user.getEmail(),password);
            mainPageWindow = new Stage();
            Main.window.close();
            Parent mainPage = FXMLLoader.load(LoginBox.class.getResource("/Ui/MainPage/MainScreen.fxml"));
            mainPageWindow.initModality(Modality.APPLICATION_MODAL);
            scene = new Scene(mainPage);
            mainPageWindow.setScene(scene);
            userProfileButton = (Button)scene.lookup("#userProfileButton");
            userProfilePic = (ImageView)scene.lookup("#userProfilePic");
            homeButton = (Button)scene.lookup("#homeButton");
            searchButton = (Button)scene.lookup("#searchButton");
            yourLibraryButton = (Button)scene.lookup("#yourLibraryButton");
            addPlaylistButton = (Button)scene.lookup("#addPlaylistButton");
            yourPlaylistsButton = (Button)scene.lookup("#yourPlaylistsButton");
            setCredentials();
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
            userProfileButton.setOnAction(actionEvent -> {
                handleUserProfileButton();
            });
        }
        catch (Exception e){
            System.out.println("Error while loading main page");
            e.printStackTrace();
        }



    }
    public void handleUserProfileButton()  {
        ProfilePageController ProfilePageController = new ProfilePageController();
        user.display();

        ProfilePageController.loadWindow(user);
    }

    public void handleSearchButton()  {
        SearchPageController searchController = new SearchPageController();
        searchController.loadWindow(MainScreenController.getUser());
//        user.display();
//        searchController.loadWindow();
    }

    private void setCredentials(){
        userProfileButton.setText(user.getName());
    }

    public void handleAddpPlaylistButton(){
        AddPlaylistController p =  new AddPlaylistController();
        try {
            p.loadWindow();
        } catch (IOException e) {
            System.out.println("Error loading add playlist window");
            e.printStackTrace();
        }
    }

    public static User getUser() {
        return user;
    }
    public void handleYourLibraryButton(){
        try{
            YourLibraryController p = new YourLibraryController();
            p.loadwindow();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void handleEventsButton(){
        AllEvents ae = new AllEvents();
        ae.ALLEventsInterface();
    }

    private void play_playlist_button(boolean isplaylist, int p_id, Image coverart)
    {
        PlayerController pc = new PlayerController();
        if(isplaylist)
            pc.play_playlist(p_id);
        else
            pc.play_single_song(p_id);
    }

    public void playartist(MouseEvent me)
    {
        int p_id = 101;
        boolean isplaylist = true;
        Image coverart;
        ImageView clicked = (ImageView) me.getSource();
        coverart = clicked.getImage();
        if(clicked.getId().equals(a1.getId())){p_id = 101; coverart = a1.getImage();}
        else if(clicked.getId().equals(a2.getId())){p_id = 102;coverart = a2.getImage();}
        else if(clicked.getId().equals(a3.getId())){p_id = 106;coverart = a3.getImage();}
        else if(clicked.getId().equals(a4.getId())){p_id = 105;coverart = a4.getImage();}
        else if(clicked.getId().equals(a5.getId())){p_id = 103;coverart = a5.getImage();}
        else if(clicked.getId().equals(a6.getId())){p_id = 104;coverart = a6.getImage();}
        else if(clicked.getId().equals(g1.getId())){p_id = 107;coverart = g1.getImage();}
        else if(clicked.getId().equals(g2.getId())){p_id = 109;coverart = g2.getImage();}
        else if(clicked.getId().equals(g3.getId())){p_id = 111;coverart = g3.getImage();}
        else if(clicked.getId().equals(g4.getId())){p_id = 112;coverart = g4.getImage();}
        else if(clicked.getId().equals(g5.getId())){p_id = 113;coverart = g5.getImage();}
        else if(clicked.getId().equals(g6.getId())){p_id = 110;coverart = g6.getImage();}
        else if(clicked.getId().equals(s1.getId())){p_id = 2; isplaylist = false;coverart = s1.getImage();}
        else if(clicked.getId().equals(s2.getId())){p_id = 13; isplaylist = false;coverart = s2.getImage();}
        else if(clicked.getId().equals(s3.getId())){p_id = 1; isplaylist = false;coverart = s3.getImage();}
        else if(clicked.getId().equals(s4.getId())){p_id = 7; isplaylist = false;coverart = s4.getImage();}
        else if(clicked.getId().equals(s5.getId())){p_id = 27; isplaylist = false;coverart = s5.getImage();}
        else if(clicked.getId().equals(s6.getId())){p_id = 35; isplaylist = false;coverart = s6.getImage();}

        System.out.println(p_id + "  " + isplaylist);

        play_playlist_button(isplaylist, p_id, coverart);
    }

    public void handleYourPlayLists(){
        try{
            YourLibraryController p = new YourLibraryController();
            p.loadwindow();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void handleMyBookings(){
        try{
            MyBookingsController mbc = new MyBookingsController();
            mbc.loadWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
