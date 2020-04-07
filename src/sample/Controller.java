package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    public  Button mainlogin;
    public Button mainsignup;

    public  void handlelogin(){
        try{
            LoginBox.getLogin();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void handlesignup(){
       try{
           LoginBox.getSignup();
       }
       catch (Exception e){
           e.printStackTrace();
       }
    }
}
