package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Parent root;
    public static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        window.setTitle("BAJAO application");
        window.setScene(new Scene(root, 1080, 720));
        window.setResizable(false);
        window.show();
        DBConnection db = new DBConnection();
        db.getconnction();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
