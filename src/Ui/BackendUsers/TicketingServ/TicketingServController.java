package Ui.BackendUsers.TicketingServ;

import Database.DBConnectionBanking;
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

import java.io.IOException;
import java.sql.*;
import java.util.Random;
import Exception.*;
public class TicketingServController {

    public AnchorPane activityWindow;
    public Stage ticketingServWindow;
    public Scene scene;
    public void loadWindow() throws IOException {
        ticketingServWindow = new Stage();
        Parent page = FXMLLoader.load(getClass().getResource("TicketingServ.fxml"));
        ticketingServWindow.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(page);
        ticketingServWindow.setScene(scene);

        ticketingServWindow.setTitle("Ticketing Services");
        ticketingServWindow.setResizable(false);
        ticketingServWindow.show();
    }

    public void handleAddTickets(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField lp_id = new TextField();
        lp_id.opacityProperty().setValue(.5);
        lp_id.setPromptText("Live Performance Id");
        vb.getChildren().add(lp_id);

        TextField name = new TextField();
        name.opacityProperty().setValue(.5);
        name.setPromptText("Ticketing Service Name");
        vb.getChildren().add(name);

        TextField amount = new TextField();
        amount.opacityProperty().setValue(.5);
        amount.setPromptText("Rate per Ticket");
        vb.getChildren().add(amount);

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
            DBConnectionTicketingServ db = new DBConnectionTicketingServ();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = "Select * from TICKETING_SERVICE where name = '" + name.getText()+ "'";
                    System.out.println(query);
                    ResultSet rs = stmt.executeQuery(query);
                    int tic_serv_id;
                    rs.next();
                    tic_serv_id = rs.getInt("tic_serv_id");

                    if(rs.getRow() == 0){
                        throw  new InvalidEntryException("Artist not found");
                    }

                    Random rand = new Random();
                    int r = rand. nextInt(9000000) + 1000000;
                    query = "insert into TICKETS values(" + r +"," + Integer.parseInt(lp_id.getText() )+ "," + tic_serv_id + ","+ Float.parseFloat(amount.getText()) + ","+null+")";
                    System.out.println(query);
                    stmt.executeUpdate(query);

                    respone.setText("Successfully Added Ticket ");
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

    public  void handleRemoveTickets(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField tic_id = new TextField();
        tic_id.opacityProperty().setValue(.5);
        tic_id.setPromptText("Ticket ID");
        vb.getChildren().add(tic_id);

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
            DBConnectionTicketingServ db = new DBConnectionTicketingServ();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();

                    String query = "delete from TICKETS where ticket_id = " + tic_id.getText() ;
                    System.out.println(query);
                    stmt.executeUpdate(query);

                    respone.setText("Successfully Removed Ticket ");
                    connection.close();
                } catch (SQLException throwables) {
                    respone.setText("Something went wrong !!!");
                    throwables.printStackTrace();
                }

            }
        });
    }

    public void handleAllServices(){

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
            DBConnectionTicketingServ db = new DBConnectionTicketingServ();
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
            DBConnectionTicketingServ db = new DBConnectionTicketingServ();
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
}


