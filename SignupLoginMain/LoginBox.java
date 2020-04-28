package Ui.SignupLoginMain;
import Core.SaveFileCache;
import Core.User;
import Exception.*;
import Database.DataController;
import Ui.MainPage.MainScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

//import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoginBox {
    public static Stage loginwindow;
    public static Stage signupwindow;


    public static void getLogin() throws IOException {
        SaveFileCache save = null;
        try {
            save = deserialize();
        } catch (ClassNotFoundException e) {
            System.out.println("Could not Deserialize1");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Could not Deserialize2");

            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not Deserialize3");

            e.printStackTrace();
        }

        loginwindow = new Stage();
        loginwindow.initModality(Modality.APPLICATION_MODAL);
        Parent login = FXMLLoader.load(LoginBox.class.getResource("LoginBox.fxml"));
        Scene scene1 = new Scene(login, 1280,720);
        if(save !=null && save.getStatus()){
            TextField email = (TextField)scene1.lookup("#loginusername"); //this is actaully user email
            PasswordField password = (PasswordField)scene1.lookup("#loginpassword");
            email.setText(save.getUserEmail());
            password.setText(save.getUserPassword());
        }
        Button loginbutton = (Button)scene1.lookup("#loginbutton");
        loginbutton.setOnAction(event -> {
            TextField email = (TextField)scene1.lookup("#loginusername"); //this is actaully user email
            PasswordField password = (PasswordField)scene1.lookup("#loginpassword");
            try{
                DataController cont = new DataController();
                User user;
                user = cont.validateLogin(email.getText(),password.getText());
                user.display();
                System.out.println("SuccessFull");
                loginwindow.close();
                MainScreenController msc = new MainScreenController();
                msc.loadWindow(user,password.getText());

            } catch (MyException e) {
                System.out.println("Error occured during login");
                e.printStackTrace();
            }
        });
        Hyperlink signup = (Hyperlink)scene1.lookup("#mainLoginSignup");
        signup.setOnAction(actionEvent -> {
            try {
                getSignup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        loginwindow.setScene(scene1);
        loginwindow.setTitle("Login");
        loginwindow.setResizable(false);
        loginwindow.showAndWait();
    }

    public static void getSignup() throws IOException {
        signupwindow = new Stage();
        signupwindow.initModality(Modality.APPLICATION_MODAL);
        Parent login = FXMLLoader.load(LoginBox.class.getResource("Signup.fxml"));
        ToggleGroup group = new ToggleGroup();
        Scene scene1 = new Scene(login);
        RadioButton m = (RadioButton)scene1.lookup("#gendermale");
        RadioButton f = (RadioButton)scene1.lookup("#genderfemale");
        RadioButton o = (RadioButton)scene1.lookup("#genderother");
        m.setToggleGroup(group);
        f.setToggleGroup(group);
        o.setToggleGroup(group);
        group.getSelectedToggle();
        signupwindow.setScene(scene1);
        signupwindow.setTitle("Register");
        signupwindow.setResizable(false);
        signupwindow.show();
    }

    public static SaveFileCache deserialize() throws IOException,ClassNotFoundException, FileNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("Cache/Credentials.txt"));
            SaveFileCache p = (SaveFileCache) in.readObject();
            return p;
        }
        finally {
            if(in!=null)in.close();
        }
    }
}
