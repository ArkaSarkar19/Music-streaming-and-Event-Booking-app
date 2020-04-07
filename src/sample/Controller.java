package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
