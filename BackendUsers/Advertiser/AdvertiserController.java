package Ui.BackendUsers.Advertiser;

import Database.DBConnectionAdvertiser;
import Database.DBConnectionBanking;
import Database.DBConnectionTicketingServ;
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
import java.sql.*;
import java.util.Random;

import Exception.*;
public class AdvertiserController {

    public AnchorPane activityWindow;
    public Stage advertiserWindow;
    public Scene scene;
    public void loadWindow() throws IOException {
        advertiserWindow = new Stage();
        Parent page = FXMLLoader.load(getClass().getResource("Advertiser.fxml"));
        advertiserWindow.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(page);
        advertiserWindow.setScene(scene);

        advertiserWindow.setTitle("Advertiser");
        advertiserWindow.setResizable(false);
        advertiserWindow.show();
    }

    public void handlePostAd(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField name = new TextField();
        name.opacityProperty().setValue(.5);
        name.setPromptText("Advertiser Name");
        vb.getChildren().add(name);

        Button ok = new Button("OK");
        vb.getChildren().add(ok);
        ok.setPrefWidth(100);
        Label label = new Label("Response from Server");
        label.setTextFill(Color.web("#e0e8d5"));

        vb.getChildren().add(label);

        TextArea respone = new TextArea();
        respone.opacityProperty().setValue(.6);
        respone.setMinSize(100,250);
        vb.getChildren().add(respone);
        activityWindow.getChildren().add(vb);

        ok.setOnAction(actionEvent -> {
            DBConnectionAdvertiser db = new DBConnectionAdvertiser();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = "Select * from ADVERTISERS where name = '" + name.getText()+ "'";
                    System.out.println(query);
                    ResultSet rs = stmt.executeQuery(query);
                    int advertiser_id;
                    rs.next();
                    advertiser_id = rs.getInt("advertiser_id");

                    if(rs.getRow() == 0){
                        throw  new InvalidEntryException("Advertiser not found");
                    }

                    Random rand = new Random();
                    int r = rand. nextInt(9000000) + 1000000;
                    query = "insert into ADVERTISEMENTS values(" + r +"," +advertiser_id + ","+null+")";
                    System.out.println(query);
                    stmt.executeUpdate(query);

                    respone.setText("Successfully Added Advertisement ");
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

    public void handleRemoveAd(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField ad_id = new TextField();
        ad_id.opacityProperty().setValue(.5);
        ad_id.setPromptText("Advertisement ID");
        vb.getChildren().add(ad_id);

        Button ok = new Button("OK");
        vb.getChildren().add(ok);
        ok.setPrefWidth(100);
        Label label = new Label("Response from Server");
        label.setTextFill(Color.web("#e0e8d5"));

        vb.getChildren().add(label);

        TextArea respone = new TextArea();
        respone.opacityProperty().setValue(.6);
        respone.setMinSize(100,250);
        vb.getChildren().add(respone);
        activityWindow.getChildren().add(vb);

        ok.setOnAction(actionEvent -> {
            DBConnectionAdvertiser db = new DBConnectionAdvertiser();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = "Select *  from  ADVERTISEMENTS where ad_id = " + Integer.parseInt(ad_id.getText())+ "";
                    ResultSet rs = stmt.executeQuery(query);
                    rs.last();
                    if(rs.getRow() == 0){
                        throw  new InvalidEntryException("Advertiser not found");
                    }

                    query = "delete from  ADVERTISEMENTS where ad_id = " + Integer.parseInt(ad_id.getText())+ "";
                    System.out.println(query);
                    stmt.executeUpdate(query);
                    respone.setText("Successfully Deleted Advertisement ");
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

    public void handleLivePerformances(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        Button ok = new Button("Show all Live Performances");
        vb.getChildren().add(ok);
        ok.setMinWidth(100);
        Label label = new Label("Response from Server");
        label.setTextFill(Color.web("#e0e8d5"));

        vb.getChildren().add(label);

        TextArea respone = new TextArea();
        respone.opacityProperty().setValue(.6);
        respone.setMinSize(100,350);
        vb.getChildren().add(respone);
        activityWindow.getChildren().add(vb);

        ok.setOnAction(actionEvent -> {
            respone.clear();
            DBConnectionTicketingServ db = new DBConnectionTicketingServ();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = "SELECT * FROM LIVE_PERFORMANCES";
                    System.out.println(query);
                    ResultSet rs = stmt.executeQuery(query);
                    int k = 0;
                    ResultSetMetaData rsmd = rs.getMetaData();
                    String s1 = rsmd.getColumnName(1) + " | " + rsmd.getColumnName(2) + " | " + rsmd.getColumnName(3) + " | " + rsmd.getColumnName(4) + " | " + rsmd.getColumnName(5) + " | " + rsmd.getColumnName(6) + " | " + rsmd.getColumnName(7) + "\n" ;
                    respone.appendText(s1);
                    while (rs.next()) {
                        ++k;
                        String s = rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getString(5) + " " + rs.getFloat(6) + " " + rs.getInt(7) + "\n" ;
                        respone.appendText(s);
                    }
                    if (k == 0) {
                        throw new InvalidEntryException("Entry not found");
                    }


                    respone.appendText("Done ");

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

    public void handleAdvertisements(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField advertiser = new TextField();
        advertiser.opacityProperty().setValue(.5);
        advertiser.setPromptText("Advertiser Name");
        vb.getChildren().add(advertiser);

        Button ok = new Button("OK");
        vb.getChildren().add(ok);
        ok.setPrefWidth(100);
        Label label = new Label("Response from Server");
        label.setTextFill(Color.web("#e0e8d5"));

        vb.getChildren().add(label);

        TextArea respone = new TextArea();
        respone.opacityProperty().setValue(.6);
        respone.setMinSize(100,350);
        vb.getChildren().add(respone);
        activityWindow.getChildren().add(vb);

        ok.setOnAction(actionEvent -> {
            respone.clear();
            DBConnectionAdvertiser db = new DBConnectionAdvertiser();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    int check = 0;
                    boolean r1 = advertiser.getText().equals("All");


                    if(advertiser.getText().equals("")) check = 1;
                    String query = null;
                    if(check == 0) {

                        int m  = 0;
                        if(!r1) query = "select * from ADVERTISERS where name = '"+ advertiser.getText() + "'";
                        else{
                            query = "select * from ADVERTISEMENTS";
                            m = 1;
                        }
                        if(m == 1) {
                            System.out.println(query);
                            ResultSet rs = stmt.executeQuery(query);
                            int k = 0;
                            ResultSetMetaData rsmd = rs.getMetaData();
                            String s1 = rsmd.getColumnName(1) + " | " + rsmd.getColumnName(2) + " | " + rsmd.getColumnName(3) + "\n";
                            respone.appendText(s1);
                            while (rs.next()) {
                                ++k;
                                String s = rs.getInt(1) + " " + rs.getInt(2) +  "\n";
                                respone.appendText(s);
                            }
                            if (k == 0) {
                                throw new InvalidEntryException("Entry not found");
                            }


                            respone.appendText("Done ");
                        }
                        else{
                            System.out.println(query);
                            ResultSet rs = stmt.executeQuery(query);
                            rs.next();
                            int advertiser_id = rs.getInt("advertiser_id");
                            query = "select * from ADVERTISEMENTS where advertiser_id = "+ advertiser_id + "";
                            ResultSet rs2 = stmt.executeQuery(query);
                            int k = 0;
                            ResultSetMetaData rsmd = rs2.getMetaData();
                            String s1 = rsmd.getColumnName(1) + " | " + rsmd.getColumnName(2) + " | " + rsmd.getColumnName(3) + "\n";
                            respone.appendText(s1);
                            while (rs2.next()) {
                                ++k;
                                String s = rs2.getInt(1) + " " + rs2.getInt(2) +  "\n";
                                respone.appendText(s);
                            }
                            if (k == 0) {
                                throw new InvalidEntryException("Entry not found");
                            }

                            respone.appendText("Done ");
                        }
                    }
                    else{
                        respone.setText("Empty Entry !!!!!!!!");
                    }
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
