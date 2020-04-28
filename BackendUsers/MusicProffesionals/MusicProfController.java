package Ui.BackendUsers.MusicProffesionals;

import Database.DBConnectionMusicProf;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import Exception.*;

public class MusicProfController {
    public AnchorPane activityWindow;
    public Stage musicProfWindow;
    public Scene scene;
    public void loadWindow() throws IOException {
        musicProfWindow = new Stage();
        Parent page = FXMLLoader.load(getClass().getResource("MusicProf.fxml"));
        musicProfWindow.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(page);
        musicProfWindow.setScene(scene);

        musicProfWindow.setTitle("Musical Proffesional");
        musicProfWindow.setResizable(false);
        musicProfWindow.show();
    }
    public void handleAddsong(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField title = new TextField();
        title.opacityProperty().setValue(.5);
        title.setPromptText("Title");
        vb.getChildren().add(title);

        TextField lang = new TextField();
        lang.opacityProperty().setValue(.5);
        lang.setPromptText("Language");
        vb.getChildren().add(lang);

        TextField artist = new TextField();
        artist.opacityProperty().setValue(.5);
        artist.setPromptText("Artist");
        vb.getChildren().add(artist);

        TextField album = new TextField();
        album.opacityProperty().setValue(.5);
        album.setPromptText("Album");
        vb.getChildren().add(album);

        TextField genre = new TextField();
        genre.opacityProperty().setValue(.5);
        genre.setPromptText("Genre");
        vb.getChildren().add(genre);

        TextField duration = new TextField();
        duration.opacityProperty().setValue(.5);
        duration.setPromptText("Duration");
        vb.getChildren().add(duration);

        Button ok = new Button("OK");
        vb.getChildren().add(ok);
        ok.setPrefWidth(100);
        Label label = new Label("Response from Server");
        label.setTextFill(Color.web("#e0e8d5"));

        vb.getChildren().add(label);

        TextArea respone = new TextArea();
        respone.opacityProperty().setValue(.6);
        respone.setMinSize(100,200);
        vb.getChildren().add(respone);
        activityWindow.getChildren().add(vb);

        ok.setOnAction(actionEvent -> {
            DBConnectionMusicProf db = new DBConnectionMusicProf();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = "Select * from ALL_ARTISTS where name = '" + artist.getText()+ "'";
                    System.out.println(query);
                    ResultSet rs = stmt.executeQuery(query);
                    int artist_id;
                    int album_id;
                    rs.next();
                    artist_id = rs.getInt("artist_id");

                    if(rs.getRow() == 0){
                        throw  new InvalidEntryException("Artist not found");
                    }

                    query = "Select * from ALBUMS where name = '" + album.getText()+ "'";
                    System.out.println(query);
                    ResultSet rs2 = stmt.executeQuery(query);
                    rs2.next();
                    album_id = rs2.getInt("album_id");
                    if(rs2.getRow() == 0){
                        throw  new InvalidEntryException("ALbum not found");
                    }
                    Random rand = new Random();
                    int r = rand. nextInt(9000000) + 1000000;
                    query = "insert into ALL_SONGS values(" + r +",'" + title.getText() + "','" + lang.getText() + "',"+ artist_id + ","+ album_id + "," + 0  + ",'" + duration.getText() + "'," + "'2020-04-30'" +",'" + genre.getText() + "')";
                    System.out.println(query);
                    stmt.executeUpdate(query);
                    query = "insert into SONG_GENRE values(" + r + ",'" + genre.getText() + "')";
                    System.out.println(query);
                    stmt.executeUpdate(query);
                    respone.setText("Successfully Added song ");
                    connection.close();
                } catch (SQLException throwables) {
                    respone.setText("Something went wrong !!!");
                    throwables.printStackTrace();
                }
                catch (InvalidEntryException e){
                    respone.setText(e.getMessage());
                }
            }
        });

    }

    public void handleAddAlbum(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField title = new TextField();
        title.opacityProperty().setValue(.5);
        title.setPromptText("Name");
        vb.getChildren().add(title);

        TextField artist = new TextField();
        artist.opacityProperty().setValue(.5);
        artist.setPromptText("Artist");
        vb.getChildren().add(artist);


        TextField label = new TextField();
        label.opacityProperty().setValue(.5);
        label.setPromptText("Label");
        vb.getChildren().add(label);

        TextField genre = new TextField();
        genre.opacityProperty().setValue(.5);
        genre.setPromptText("Genre");
        vb.getChildren().add(genre);

        Button ok = new Button("OK");
        vb.getChildren().add(ok);
        ok.setPrefWidth(100);
        Label label1 = new Label("Response from Server");
        label1.setTextFill(Color.web("#e0e8d5"));

        vb.getChildren().add(label1);

        TextArea respone = new TextArea();
        respone.opacityProperty().setValue(.6);
        respone.setMinSize(100,250);
        vb.getChildren().add(respone);
        activityWindow.getChildren().add(vb);

        ok.setOnAction(actionEvent -> {
            DBConnectionMusicProf db = new DBConnectionMusicProf();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = "Select * from ALL_ARTISTS where name = '" + artist.getText()+ "'";
                    System.out.println(query);
                    ResultSet rs = stmt.executeQuery(query);
                    int artist_id;
                    int album_id;
                    rs.next();
                    if(rs.getRow() == 0) throw  new InvalidEntryException("ALbum not found");
                    artist_id = rs.getInt("artist_id");

                    Random rand = new Random();
                    int r = rand. nextInt(9000000) + 1000000;

                    query = "insert into ALBUMS values(" + r +",'" + title.getText() + "',"+ artist_id + ",'" + label.getText()+ "'," + "'2020-04-30'" + ",'" + genre.getText() + "')";
                    System.out.println(query);
                    stmt.executeUpdate(query);
                    query = "insert into ALBUM_GENRE values(" + r + ",'" + genre.getText() + "')";
                    System.out.println(query);
                    stmt.executeUpdate(query);
                    respone.setText("Successfully Added Album ");
                    connection.close();
                } catch (SQLException throwables) {
                    respone.setText("Something went wrong !!!");
                    throwables.printStackTrace();
                }
                catch (InvalidEntryException e){
                    respone.setText(e.getMessage());
                }
            }
        });
    }

    public void handleRemoveSong(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField title = new TextField();
        title.opacityProperty().setValue(.5);
        title.setPromptText("Name");
        vb.getChildren().add(title);

        Button ok = new Button("OK");
        vb.getChildren().add(ok);
        ok.setPrefWidth(100);
        Label label1 = new Label("Response from Server");
        label1.setTextFill(Color.web("#e0e8d5"));

        vb.getChildren().add(label1);

        TextArea respone = new TextArea();
        respone.opacityProperty().setValue(.6);
        respone.setMinSize(100,250);
        vb.getChildren().add(respone);
        activityWindow.getChildren().add(vb);

        ok.setOnAction(actionEvent -> {
            DBConnectionMusicProf db = new DBConnectionMusicProf();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = "Select * from ALL_SONGS where title = '" + title.getText()+ "'";
                    System.out.println(query);
                    ResultSet rs = stmt.executeQuery(query);
                    int song_id;
                    rs.next();
                    if(rs.getRow() == 0) throw  new InvalidEntryException("Song not found");
                    song_id = rs.getInt("song_id");

                    Random rand = new Random();
                    int r = rand. nextInt(9000000) + 1000000;
                    query = "delete from SONG_GENRE where song_id = " + song_id ;
                    System.out.println(query);
                    stmt.executeUpdate(query);
                    query = "delete from ALL_SONGS where song_id = " + song_id ;
                    System.out.println(query);
                    stmt.executeUpdate(query);
                    respone.setText("Successfully Removed Song ");
                    connection.close();
                } catch (SQLException throwables) {
                    respone.setText("Something went wrong !!!");
                    throwables.printStackTrace();
                }
                catch (InvalidEntryException e){
                    respone.setText(e.getMessage());
                }
            }
        });
    }

}
