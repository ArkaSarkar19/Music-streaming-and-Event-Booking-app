package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;

public class Controller {
    @FXML
    public static Button mainlogin;
    @FXML
    public  void handlelogin(){
        try{
            LoginBox.getLogin();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
