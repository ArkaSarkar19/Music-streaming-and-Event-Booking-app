package Ui.SignupLoginMain;
import Core.User;
import Core.UserAuth;
import Database.DBConnection;
import Database.DataController;
import Exception.*;
import Ui.BackendUsers.MasterController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginSignupController {

    public Stage window;

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
    public Button adminButton;
    public  void handlelogin(){
        try{
            LoginBox.getLogin();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void handleAdminButton(){
        try{
            MasterController mc = new MasterController();
            mc.loadSelectWindow();
            Main.window.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handlesignup(){
       try{
           LoginBox.getSignup();
       }
       catch ( IOException e){
           e.printStackTrace();
       }
    }

    public void handlemainsignup() throws MyException {
        try {
            boolean done = true;
            String name = signupname.getText().toString();
            if (name.equals("")) {
                a1.setText("Required ! ");
                done = false;
            } else a1.setText("");
            String username = signupusername.getText().toString();
            if (username.equals("")) {
                a2.setText("Required ! ");
                done = false;
            } else a2.setText("");
            String password = signuppassword.getText().toString();
            if (password.equals("")) {
                a4.setText("Required ! ");
                done = false;
            } else a4.setText("");
            String c_password = signuppassword1.getText().toString();
            if (c_password.equals("")) {
                a5.setText("Required ! ");
                done = false;
            } else a5.setText("");
            if (!password.equals(c_password)) {
                a5.setText("Passwords do not match");
                a4.setText("Passwords do not match");
                done = false;
            }
            String country = signupcountry.getText().toString();
            if (country.equals("")) {
                a8.setText("Required ! ");
                done = false;
            } else a8.setText("");
            String email = signupemail.getText().toString();
            if (email.equals("")) {
                a3.setText("Required ! ");
                done = false;
            } else a3.setText("");
            String month = signupmonth.getText().toString();
            String year = signupyear.getText().toString();
            String date = signupdate.getText().toString();
            String gender = "";
            if (month.equals("Month") || year.equals("") || date.equals("")) {
                a6.setText("Required ! ");
                done = false;
            } else a6.setText("");

            if (gendermale.isSelected() || genderfemale.isSelected() || genderother.isSelected()) {
                if (gendermale.isSelected()) gender = "Male";
                else if (genderfemale.isSelected()) gender = "Female";
                else if (genderother.isSelected()) gender = "Other";
                else ;
                a7.setText("");
            } else {
                a7.setText("Required ! ");
                done = false;
            }
            if (!done) throw new InvalidSignupException("Something went wrong during signup");
            DataController db = new DataController();
            db.checkUser(name,email);
            int r;
            while(true){
                r = (int) ((Math.random() * 9000000) + 1000000);
                try{
                    DBConnection con = new DBConnection();
                    Connection connection = con.getConnection();
                    if(connection == null) throw new ConnectionInvalidException("Connection not Establised");
                    Statement stmt = connection.createStatement();
                    String query = "insert into `8WS34TaNi5`.USER values(" + r + ",'','','','2006-09-01','')";
                    System.out.println(query);
                    connection.close();
                    break;
                }
                catch (MyException e){
                    System.out.println(e.getMessage());
                }
                catch (SQLException e) {
                    e.printStackTrace();

                }
            }
            System.out.println(r);
            System.out.println(year+"-"+signupmonth.getText().toString()+"-"+date);
            User new_user = new User(r,name,country,email,year+"-"+signupmonth.getText().toString()+"-"+date,gender);
            new_user.checkFormatting();
            UserAuth newUserAuth = new UserAuth(r,password);
            newUserAuth.checkFormatting();
            DataController db1 = new DataController();
            db1.addUser(new_user,newUserAuth);
            LoginBox.signupwindow.close();

        }
        catch (InvalidSignupException e){
            System.out.println(e.getMessage());
        }
        catch (UserExistsException e){
            System.out.println(e.getMessage());
        }
        catch (InvalidEmailException e ){
            System.out.println(e.getMessage());
        }
        catch (InvalidPasswordException e){
            System.out.println(e.getMessage());
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void handlemonthsignup1(){signupmonth.setText("01");}
    public void handlemonthsignup2(){signupmonth.setText("02");}
    public void handlemonthsignup3(){signupmonth.setText("03");}
    public void handlemonthsignup4(){signupmonth.setText("04");}
    public void handlemonthsignup5(){signupmonth.setText("05");}
    public void handlemonthsignup6(){signupmonth.setText("06");}
    public void handlemonthsignup7(){signupmonth.setText("07");}
    public void handlemonthsignup8(){signupmonth.setText("08");}
    public void handlemonthsignup9(){signupmonth.setText("09");}
    public void handlemonthsignup10(){signupmonth.setText("10");}
    public void handlemonthsignup11(){signupmonth.setText("11");}
    public void handlemonthsignup12(){signupmonth.setText("12");}


}
