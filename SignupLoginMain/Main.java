package Ui.SignupLoginMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;

public class Main extends Application {
    public static Parent root;
    public static Stage window;
    private File Dir;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        root = FXMLLoader.load(getClass().getResource("/Ui/SignupLoginMain/Main.fxml"));
        window.setTitle("BAJAO");
        window.setScene(new Scene(root, 1280, 720));
        window.setResizable(false);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
