package Ui.BookEvents;

import Database.DataController;
import Ui.MainPage.MainScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Exception.*;
import java.io.IOException;

public class BookEventController {
    public String ArtistName;
    public String Venue;
    public String DateTime;
    public static double Amount;
    public static double walletbalance;
    public static Stage bookEventsWindow;
    public Scene scene;
    public static int ticket_id;
    public Label artistName;
    public Label venue;
    public Label dateTime;
    public Label amount;

    public void loadWindow() throws IOException {
        bookEventsWindow = new Stage();
        Parent page = FXMLLoader.load(getClass().getResource("BookEvent.fxml"));
        bookEventsWindow.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(page);
        bookEventsWindow.setScene(scene);
        bookEventsWindow.setTitle("BookEvent");
        bookEventsWindow.setResizable(false);
        bookEventsWindow.show();

        Label aName = (Label)scene.lookup("#artistName");
        Label ven = (Label)scene.lookup("#venue");
        Label datetime = (Label)scene.lookup("#dateTime");
        Label amo = (Label)scene.lookup("#amount");
        Label walletb = (Label)scene.lookup("#walletBalance");
        aName.setText("Artist : " + ArtistName);
        ven.setText("Venue : " + Venue);
        datetime.setText("DateTime : " + DateTime);
        amo.setText("Amount : " + Amount);
        walletb.setText(" Balance : "  + walletbalance);

    }

    public void handlePayNow(){
        DataController db = new DataController();
        try {
            int trasaction_id = db.makeTransaction(MainScreenController.getUser(),Amount);
            db.makeBooking(MainScreenController.getUser(),trasaction_id,ticket_id);
            System.out.println("Booking made successfully");
            bookEventsWindow.close();
        } catch (ConnectionInvalidException e) {
            e.printStackTrace();
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
        }
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public void setArtistName(String artistName) {
        ArtistName = artistName;
    }

    public void setDateTime(String dateTime) {
        this.DateTime = dateTime;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

    public void setWalletbalance(double walletbalance) {
        this.walletbalance = walletbalance;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }
}
