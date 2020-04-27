package Ui.BackendUsers;

import Ui.BackendUsers.MusicProffesionals.MusicProfController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MasterController {
    public Button ticketingServicesButton;
    public Button advertisersButton;
    public Button adminButton;
    public Button eventOrganisersButton;
    public Button BankingServicesButton;
    public  Button musicProfessionalsButton;
    public static Stage selectWindow;
    public Scene scene;

    public void loadSelectWindow() throws IOException {
        selectWindow = new Stage();
        Parent page = FXMLLoader.load(getClass().getResource("SelectUser.fxml"));
        selectWindow.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(page);
        selectWindow.setScene(scene);
        selectWindow.setTitle("Backend Users");
        selectWindow.setResizable(false);
        selectWindow.show();
    }

    public void handleMusicProfButton(){
        MusicProfController mpf = new MusicProfController();
        try {
            mpf.loadWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleTicketingServButton(){

    }
    public void handleBankingServButton(){

    }
    public void handleAdvertiserButton(){

    }
    public void handleAdminButton(){

    }
    public void handleEventOrgButton(){

    }
}
