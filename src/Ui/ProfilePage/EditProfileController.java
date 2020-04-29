package Ui.ProfilePage;

import Core.User;
import Database.DBConnection;
import Exception.ConnectionInvalidException;
import Database.DataController;
import Ui.Search.M;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

//import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EditProfileController {
    public Connection connection;
    public static Scene scene;
    public static Stage editProfilePage;

    public TextField nameField;
    public Label resultText;
    public TextField countryField;
    public TextField emailField;
    public TextField dobField;

    public Button applyButton;
    public Button backButton;

    public ArrayList<String> editedData;


    public void loadWindow(User user) throws IOException {

        try {
            editProfilePage = new Stage();
            editProfilePage.initModality(Modality.APPLICATION_MODAL);
            Parent profile = FXMLLoader.load(EditProfileController.class.getResource("editProfilePage.fxml"));
            scene = new Scene(profile);
            editProfilePage.setScene(scene);
            editProfilePage.setTitle("Edit Profile");
            editProfilePage.setResizable(false);
            editProfilePage.show();

        }
        catch (IOException e){
            System.out.println("Error Loading Window");
        }



        applyButton = (Button) scene.lookup("#applyButton") ;
        applyButton.setOnAction(actionEvent -> {

            editedData = new ArrayList<String>();
            editedData.clear();

            nameField = (TextField)scene.lookup("#nameField");
            if(nameField.getText().trim().isEmpty()){
                editedData.add("null");
            }
            else {
                editedData.add(nameField.getText());
            }

            countryField = (TextField)scene.lookup("#countryField");
            if(countryField.getText().trim().isEmpty()){
                editedData.add("null");
            }
            else {
                editedData.add(countryField.getText());
            }

            emailField = (TextField)scene.lookup("#emailField");
            if(emailField.getText().trim().isEmpty()){
                editedData.add("null");
            }
            else {
                editedData.add(emailField.getText());
            }

            dobField = (TextField)scene.lookup("#dobField");
            if(dobField.getText().trim().isEmpty()){
                editedData.add("null");
            }
            else {
                editedData.add(dobField.getText());
            }

            DataController dc = new DataController();
            try {
                dc.updateUserData(editedData,user);
                resultText = (Label) scene.lookup("#resultText");
                resultText.setText("Details updated! Go Back");
            } catch (Exception e) {
                e.printStackTrace();
            }

//            resultText = (TextField)scene.lookup("#resultText");


        });

        backButton = (Button) scene.lookup("#backButton") ;
        backButton.setOnAction(actionEvent -> {

            editProfilePage.close();
            ProfilePageController p = new ProfilePageController();
            p.closeWindow();
        });

    }
}
