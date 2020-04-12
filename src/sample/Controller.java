package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.math.*;
import java.awt.*;

public class Controller {
    public  Button mainlogin;
    public Button mainsignup;
    public TextField signupname;
    public TextField signupusername;
    public TextField signupemail;
    public TextField signuppassword;
    public TextField signuppassword1;
    public MenuButton signupmonth;
    public TextField signupyear;
    public TextField signupdate;
    public TextField signupcountry;
    public Button signupbutton;
    public RadioButton gendermale;
    public RadioButton genderfemale;
    public RadioButton genderother;
    public Text a1;
    public Text a2;
    public Text a3;
    public Text a4;
    public Text a5;
    public Text a6;
    public Text a7;
    public Text a8;


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

    public void handlemainsignup(){
        String name = signupname.getText().toString();
        if(name.equals("")) a1.setText("Required ! ");
        String username = signupusername.getText().toString();
        if(username.equals("")) a2.setText("Required ! ");
        String password = signuppassword.getText().toString();
        if(password.equals("")) a4.setText("Required ! ");
        String c_password = signuppassword1.getText().toString();
        if(c_password.equals("")) a5.setText("Required ! ");
        String country = signupcountry.getText().toString();
        if(country.equals("")) a8.setText("Required ! ");
        String email = signupemail.getText().toString();
        if(email.equals("")) a3.setText("Required ! ");
        int r = (int)((Math.random()*9000000)+1000000);
        System.out.println(r);
        System.out.println(name.equals(""));
    }
}
