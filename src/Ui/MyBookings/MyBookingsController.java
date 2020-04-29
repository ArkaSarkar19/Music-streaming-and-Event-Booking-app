package Ui.MyBookings;

import Database.DBConnection;
import Database.DBConnectionEventOrg;
import Database.DBConnectionTicketingServ;
import Ui.MainPage.MainScreenController;
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

public class MyBookingsController {

    public AnchorPane activityWindow;
    public Stage myBookingsWindow;
    public Scene scene;
    public void loadWindow() throws IOException {
        myBookingsWindow = new Stage();
        Parent page = FXMLLoader.load(getClass().getResource("MyBookings.fxml"));
        myBookingsWindow.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(page);
        myBookingsWindow.setScene(scene);

        myBookingsWindow.setTitle("My Booking");
        myBookingsWindow.setResizable(false);
        myBookingsWindow.show();
    }

    public void handleShowAllBookings(){
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
            DBConnection db = new DBConnection();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = "SELECT * FROM BOOKINGS where user_id = " + MainScreenController.getUser().getUser_id();
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

    public void handleRemoveBooking(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField booking_id = new TextField();
        booking_id.opacityProperty().setValue(.5);
        booking_id.setPromptText("Booking ID");
        vb.getChildren().add(booking_id);

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
            String query ;
            Statement stmt = null;
            DBConnection db = new DBConnection();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                     stmt = connection.createStatement();
                    query = "start transaction";
                    System.out.println(query);
                    stmt.executeUpdate(query);

                    query = "update BOOKINGS set status = " + "'cancelled'" + "where booking_id = "  + Integer.parseInt(booking_id.getText());
                    System.out.println(query);
                    stmt.executeUpdate(query);

                    query = "select ticket_id from BOOKINGS where booking_id = " + Integer.parseInt(booking_id.getText());
                    System.out.println(query);
                    ResultSet rs = stmt.executeQuery(query);
                    rs.next();
                    int ticket_id = rs.getInt("ticket_id");

                    query = "select amount from TICKETS where ticket_id = " + ticket_id;;
                    System.out.println(query);
                    ResultSet rs2 = stmt.executeQuery(query);
                    rs2.next();
                    double amount = rs2.getFloat("amount");

                    query = "update USER_WALLET set amount = amount + " + amount + "where user_id = "  + MainScreenController.getUser().getUser_id();
                    System.out.println(query);
                    stmt.executeUpdate(query);

                    query = "commit";
                    System.out.println(query);
                    stmt.executeUpdate(query);

                    respone.setText("Successfully Removed Booking and money Refunded");
                    connection.close();
                } catch (SQLException throwables) {
                    respone.setText("Something went wrong !!!");
                    query = "start transaction";
                    System.out.println(query);
                    try {
                        stmt.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    throwables.printStackTrace();
                }

            }
        });
    }
}
