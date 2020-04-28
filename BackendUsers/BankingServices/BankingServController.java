package Ui.BackendUsers.BankingServices;

import Database.DBConnectionBanking;
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
import java.sql.*;
import java.util.Random;
import Exception.*;

import javax.security.auth.login.AccountNotFoundException;

public class BankingServController {

    public AnchorPane activityWindow;
    public Stage bankingServWindow;
    public Scene scene;
    public void loadWindow() throws IOException {
        bankingServWindow = new Stage();
        Parent page = FXMLLoader.load(getClass().getResource("BankingServ.fxml"));
        bankingServWindow.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(page);
        bankingServWindow.setScene(scene);

        bankingServWindow.setTitle("Banking Servives");
        bankingServWindow.setResizable(false);
        bankingServWindow.show();
    }

    public void handleCheckTransaction(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField payment = new TextField();
        payment.opacityProperty().setValue(.5);
        payment.setPromptText("Payment Type");
        vb.getChildren().add(payment);

        TextField currency = new TextField();
        currency.opacityProperty().setValue(.5);
        currency.setPromptText("Currency");
        vb.getChildren().add(currency);

        TextField amount = new TextField();
        amount.opacityProperty().setValue(.5);
        amount.setPromptText("Amount");
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
            respone.clear();
            DBConnectionBanking db = new DBConnectionBanking();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = null;
                    boolean r1 = payment.getText().equals("");
                    boolean r2 = currency.getText().equals("");
                    boolean r3 = amount.getText().equals("");

                    if(r1 && r2 && r3)query = null ;
                    else if(r1 && r2 && !r3)query = "select * from TRANSACTIONS where payment_amount =" + Float.parseFloat(amount.getText()) ;
                    else if(r1 && !r2 && r3)query = "select * from TRANSACTIONS where  currency = '" + currency.getText() + "'";
                    else if(r1 && !r2 && !r3)query = "select * from TRANSACTIONS where  currency = '" + currency.getText() + "' and payment_amount =" + Float.parseFloat(amount.getText());
                    else if(!r1 && r2 && r3) query = "select * from TRANSACTIONS where payment_type = '" + payment.getText() + "'";
                    else if(!r1 && r2 && !r3) query = "select * from TRANSACTIONS where (payment_type = '" + payment.getText() + "' and payment_amount =" + Float.parseFloat(amount.getText()) +")";
                    else if(!r1 && !r2 && r3) query = "select * from TRANSACTIONS where (payment_type = '" + payment.getText() + "' and  currency = '" + currency.getText() +"')";
                    else if(!r1 && !r2 && !r3) query = "select * from TRANSACTIONS where (payment_type = '" + payment.getText() + "' and  currency = '" + currency.getText() + "' and payment_amount =" + Float.parseFloat(amount.getText()) +")";
                    else{}
                    if(query!=null) {
                        System.out.println(query);
                        ResultSet rs = stmt.executeQuery(query);
                        int k = 0;
                        while (rs.next()) {
                            ++k;
                            String s = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getFloat(4) + " " + rs.getString(5) + " " + rs.getString(6) + "\n";
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

    public void handleMakeTransaction(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField payment = new TextField();
        payment.opacityProperty().setValue(.5);
        payment.setPromptText("Payment Type");
        vb.getChildren().add(payment);

        TextField currency = new TextField();
        currency.opacityProperty().setValue(.5);
        currency.setPromptText("Currency");
        vb.getChildren().add(currency);

        TextField amount = new TextField();
        amount.opacityProperty().setValue(.5);
        amount.setPromptText("Amount");
        vb.getChildren().add(amount);

        TextField bank = new TextField();
        bank.opacityProperty().setValue(.5);
        bank.setPromptText("Bank Name");
        vb.getChildren().add(bank);


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
            respone.clear();
            DBConnectionBanking db = new DBConnectionBanking();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = null;
                    boolean r1 = payment.getText().equals("");
                    boolean r2 = currency.getText().equals("");
                    boolean r3 = amount.getText().equals("");
                    boolean r4 = bank.getText().equals("");
                        int k = 0;
                    if(r1 || r2 || r3 || r4) k = 1;


                    if(k == 0) {
                         query = "Select * from BANKS where name = '" + bank.getText()+ "'";
                        System.out.println(query);
                        ResultSet rs = stmt.executeQuery(query);
                        int bank_id;
                        rs.next();
                        bank_id = rs.getInt("bank_id");

                        if(rs.getRow() == 0){
                            throw  new InvalidEntryException("Artist not found");
                        }

                        Random rand = new Random();
                        int r = rand. nextInt(9000000) + 1000000;
                        query = "insert into TRANSACTIONS values(" + r +",'" + payment.getText() + "'," + bank_id + ","+ Float.parseFloat(amount.getText()) + ",'" + currency.getText() + "'," + null +")";
                        System.out.println(query);
                        stmt.executeUpdate(query);
                        respone.setText("Successfully made Transaction ");
                        connection.close();
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


    public void handleRefundTransaction(){

    }

    public void handleBanksButton(){
        activityWindow.getChildren().clear();
        VBox vb = new VBox(15);

        TextField bank = new TextField();
        bank.opacityProperty().setValue(.5);
        bank.setPromptText("Bank Name");
        vb.getChildren().add(bank);

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
            DBConnectionBanking db = new DBConnectionBanking();
            Connection connection = db.getConnection();
            if(connection == null) respone.setText("Connection not Establised");
            else {
                try {
                    Statement stmt = connection.createStatement();
                    String query = null;
                    boolean r1 = bank.getText().equals("All");


                    if(!r1) query = "select * from BANKS where name = '"+ bank.getText() + "'";
                    else{
                            query = "select * from BANKS";
                    }
                    if(bank.getText().equals("")) query = null;
                    if(query!=null) {
                        System.out.println(query);
                        ResultSet rs = stmt.executeQuery(query);
                        int k = 0;
                        ResultSetMetaData rsmd = rs.getMetaData();
                        String s1 = rsmd.getColumnName(1) + " | " + rsmd.getColumnName(2) + " | " + rsmd.getColumnName(3) + " | " + rsmd.getColumnName(4) + " | " + rsmd.getColumnName(5) + " | " + rsmd.getColumnName(6) + "\n" ;
                        respone.appendText(s1);
                        while (rs.next()) {
                            ++k;
                            String s = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + "\n";
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
