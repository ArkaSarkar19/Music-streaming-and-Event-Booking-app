package Ui.BackendUsers.EventOrganisers;

import Database.DBConnectionBanking;
import Database.DBConnectionEventOrg;
import Database.DBConnectionMusicProf;
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
import Exception.*;
import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class EventOrganisersController {
    public AnchorPane activityWindow;
    public Stage eventOrgWindow;
    public Scene scene;
    public void loadWindow() throws IOException {
        eventOrgWindow = new Stage();
        Parent page = FXMLLoader.load(getClass().getResource("EventOrganisers.fxml"));
        eventOrgWindow.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(page);
        eventOrgWindow.setScene(scene);

        eventOrgWindow.setTitle("Event Organisers");
        eventOrgWindow.setResizable(false);
        eventOrgWindow.show();
    }

    public void handleAddLivePerformance(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField advertiser = new TextField();
        advertiser.opacityProperty().setValue(.5);
        advertiser.setPromptText("Advertiser");
        vb.getChildren().add(advertiser);

        TextField artist = new TextField();
        artist.opacityProperty().setValue(.5);
        artist.setPromptText("Artist");
        vb.getChildren().add(artist);

        TextField sponsors = new TextField();
        sponsors.opacityProperty().setValue(.5);
        sponsors.setPromptText("Sponsors");
        vb.getChildren().add(sponsors);


        TextField venue = new TextField();
        venue.opacityProperty().setValue(.5);
        venue.setPromptText("Venue");
        vb.getChildren().add(venue);


        TextField date = new TextField();
        date.opacityProperty().setValue(.5);
        date.setPromptText("Date : YYYY-MM-DD");
        vb.getChildren().add(date);

        TextField time = new TextField();
        time.opacityProperty().setValue(.5);
        time.setPromptText("Time : HH:MM:SS");
        vb.getChildren().add(time);


        TextField ticCount = new TextField();
        ticCount.opacityProperty().setValue(.5);
        ticCount.setPromptText("Total Tickets");
        vb.getChildren().add(ticCount);

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
            DBConnectionEventOrg db = new DBConnectionEventOrg();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = "Select * from ALL_ARTISTS where name = '" + artist.getText()+ "'";
                    System.out.println(query);
                    ResultSet rs = stmt.executeQuery(query);
                    int artist_id;
                    int advertiser_id;
                    rs.next();
                    artist_id = rs.getInt("artist_id");

                    if(rs.getRow() == 0){
                        throw  new InvalidEntryException("Artist not found");
                    }

                    query = "Select * from ADVERTISERS where name = '" + advertiser.getText()+ "'";
                    System.out.println(query);
                    ResultSet rs2 = stmt.executeQuery(query);
                    rs2.next();
                    advertiser_id = rs2.getInt("advertiser_id");
                    if(rs2.getRow() == 0){
                        throw  new InvalidEntryException("ALbum not found");
                    }
                    Random rand = new Random();
                    int r = rand. nextInt(9000000) + 1000000;
                    query = "insert into LIVE_PERFORMANCES values(" + r +"," + artist_id+ ",'" + sponsors.getText() + "',"+ advertiser_id + ","+ null + "," + 0 + "," + Integer.parseInt(ticCount.getText())+ ")";
                    System.out.println(query);
                    stmt.executeUpdate(query);
                    int r2 = rand. nextInt(9000000) + 1000000;
                    query = "insert into SCHEDULE values(" + r2 +"," + r+ ",'" + venue.getText() + "','" + date.getText() + " " + time.getText() +"')";
                    System.out.println(query);
                    stmt.executeUpdate(query);

                    query = "insert into LIVE_PERF_TICKETS values(" + r +","  + Integer.parseInt(ticCount.getText())+ ")";
                    System.out.println(query);
                    stmt.executeUpdate(query);

                    respone.setText("Successfully Added Performance ");
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

    public void handleCheckLikes(){

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
            DBConnectionEventOrg db = new DBConnectionEventOrg();
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
    public void handleCheckBooking(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        Button ok = new Button("Show all Bookings ");
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
            DBConnectionEventOrg db = new DBConnectionEventOrg();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = "SELECT * FROM BOOKINGS";
                    System.out.println(query);
                    ResultSet rs = stmt.executeQuery(query);
                    int k = 0;
                    ResultSetMetaData rsmd = rs.getMetaData();
                    String s1 = rsmd.getColumnName(1) + " | " + rsmd.getColumnName(2) + " | " + rsmd.getColumnName(3) + " | " + rsmd.getColumnName(4) + " | " + rsmd.getColumnName(5) +"\n" ;
                    respone.appendText(s1);
                    while (rs.next()) {
                        ++k;
                        String s = rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getInt(3) + " " + rs.getInt(4) + " " + rs.getString(5)  + "\n" ;
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

    public void handleCheckSchedule(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField live_per_id = new TextField();
        live_per_id.opacityProperty().setValue(.5);
        live_per_id.setPromptText("Live Performance ID");
        vb.getChildren().add(live_per_id);

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
            DBConnectionEventOrg db = new DBConnectionEventOrg();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = null;
                    boolean r1 = live_per_id.getText().equals("All");


                    if(!r1) query = "select * from SCHEDULE where live_perf_id = "+ Integer.parseInt(live_per_id.getText() )+ "";
                    else{
                        query = "select * from SCHEDULE";
                    }
                    if(live_per_id.getText().equals("")) query = null;
                    if(query!=null) {
                        System.out.println(query);
                        ResultSet rs = stmt.executeQuery(query);
                        int k = 0;
                        ResultSetMetaData rsmd = rs.getMetaData();
                        String s1 = rsmd.getColumnName(1) + " | " + rsmd.getColumnName(2) + " | " + rsmd.getColumnName(3) + " | " + rsmd.getColumnName(4) + "\n" ;
                        respone.appendText(s1);
                        while (rs.next()) {
                            ++k;
                            String s = rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3) + " " + rs.getString(4) + "\n";
                            respone.appendText(s);
                        }
                        if (k == 0) {
                            throw new InvalidEntryException("Entry not found");
                        }


                        respone.appendText("Done ");
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


    public void handleRemoveLivePerformances(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField live_perf_id = new TextField();
        live_perf_id.opacityProperty().setValue(.5);
        live_perf_id.setPromptText("Live Performance ID");
        vb.getChildren().add(live_perf_id);

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
            DBConnectionEventOrg db = new DBConnectionEventOrg();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query ;
                    query = "delete from SCHEDULE where live_perf_id = " + Integer.parseInt(live_perf_id.getText()) ;
                    System.out.println(query);
                    stmt.executeUpdate(query);

                    query = "delete from LIVE_PERF_TICKETS where live_perf_id = " + Integer.parseInt(live_perf_id.getText()) ;
                    System.out.println(query);
                    stmt.executeUpdate(query);

                    query = "delete from LIVE_PERFORMANCES where live_perf_id = " + Integer.parseInt(live_perf_id.getText()) ;
                    System.out.println(query);
                    stmt.executeUpdate(query);
                    respone.setText("Successfully Removed Live Performance Details ");
                    connection.close();
                } catch (SQLException throwables) {
                    respone.setText("Something went wrong !!!");
                    throwables.printStackTrace();
                }

            }
        });
    }
    public void handleAllTickets(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        Button ok = new Button("Show all Tickets");
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
            DBConnectionEventOrg db = new DBConnectionEventOrg();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = "SELECT * FROM TICKETS";
                    System.out.println(query);
                    ResultSet rs = stmt.executeQuery(query);
                    int k = 0;
                    ResultSetMetaData rsmd = rs.getMetaData();
                    String s1 = rsmd.getColumnName(1) + " | " + rsmd.getColumnName(2) + " | " + rsmd.getColumnName(3) + " | " + rsmd.getColumnName(4) + " | " + rsmd.getColumnName(5) + "\n" ;
                    respone.appendText(s1);
                    while (rs.next()) {
                        ++k;
                        String s = rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getInt(3) + " " + rs.getFloat(4) + " " + rs.getString(5)+ "\n" ;
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

    public void handleCheckAdvertiser(){
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
            DBConnectionEventOrg db = new DBConnectionEventOrg();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = null;
                    boolean r1 = advertiser.getText().equals("All");


                    if(!r1) query = "select * from ADVERTISERS where name = '"+ advertiser.getText() + "'";
                    else{
                        query = "select * from ADVERTISERS";
                    }
                    if(advertiser.getText().equals("")) query = null;
                    if(query!=null) {
                        System.out.println(query);
                        ResultSet rs = stmt.executeQuery(query);
                        int k = 0;
                        ResultSetMetaData rsmd = rs.getMetaData();
                        String s1 = rsmd.getColumnName(1) + " | " + rsmd.getColumnName(2) + " | " + rsmd.getColumnName(3) + " | " + rsmd.getColumnName(4) + " | " + rsmd.getColumnName(5)  + "\n" ;
                        respone.appendText(s1);
                        while (rs.next()) {
                            ++k;
                            String s = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) +  "\n";
                            respone.appendText(s);
                        }
                        if (k == 0) {
                            throw new InvalidEntryException("Entry not found");
                        }


                        respone.appendText("Done ");
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
